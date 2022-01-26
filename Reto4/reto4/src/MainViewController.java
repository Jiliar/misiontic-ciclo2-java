import controllers.EstudianteController;
import controllers.GeneroController;
import models.Estudiante;
import models.Genero;
import controllers.MateriaController;
import models.Materia;
import utils.GradingSystem;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.stage.StageStyle;
import java.util.List;
import java.util.LinkedList;

public class MainViewController {

    Estudiante estudiante;
    Genero genero;
    Materia materia;
    GeneroController genero_ctrl;
    MateriaController materia_ctrl;
    EstudianteController estudiante_ctrl;

    @FXML
    private Tab tabIngresarDatos;

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<Materia> cbxMateria;

    @FXML
    private ComboBox<Genero> cbxGenero;

    @FXML
    private TextField txtNota;

    @FXML
    private Button btnGuardar;

    @FXML
    private Tab tabProcesar;

    @FXML
    private Button btnCargar;

    @FXML
    private TextArea txtInput;

    @FXML
    private TextArea txtOutput;

    @FXML
    private TextArea txtResultados;

    @FXML
    private Tab tabConsultarEliminar;

    @FXML
    private TextField txtNombreConsulta;

    @FXML
    private ComboBox<Materia> cbxMateriaConsulta;

    @FXML
    private Button btnConsultar;

    @FXML
    private Button btnEliminar;

    // Evrents and Listeners
    @FXML
    private void initialize() {
        initComboBoxes();
        initTxtInput();
    }

    @FXML
    void cargarData(ActionEvent event) {
        var inputs = txtInput.getText();
        String[] data = inputs.trim().split("\n");
        List<List<Double>> data_cast = this.loadData(data);
        String ouput = this.loadDataOutput(data_cast);
        txtOutput.setText(ouput);
    }

    @FXML
    void consultarData(ActionEvent event) {
        estudiante_ctrl = new EstudianteController();
        StringBuilder dataFormated = new StringBuilder();
        var estudiante = txtNombreConsulta.getText();
        var id_materia = "";

        if (cbxMateriaConsulta.getValue() != null) {
            String materia = cbxMateriaConsulta.getValue().toString().split("-")[0].trim();
            id_materia = materia == "0" || materia.trim() == "" || materia == null ? "" : materia;
        }

        String nombre_estudiante = estudiante.length() == 0 || estudiante.trim() == "" || estudiante == null ? null
                : estudiante;

        List<Estudiante> data = estudiante_ctrl.listByEstudianteAndMateria(nombre_estudiante, id_materia);
        if (data.size() > 0) {
            for (var e : data) {
                dataFormated.append(
                        e.getNombre() + " " + Double.parseDouble(String.valueOf(e.getGenero().getIdentificador())) + " "
                                + Double.parseDouble(String.valueOf(e.getMateria().getIdentificador())) + " "
                                + e.getNota() + "\n");
            }
            txtResultados.setText(dataFormated.toString());
        } else {
            this.MsgDialog("No existen datos con los criterios ingresados", "advertencia");
        }
    }

    @FXML
    void eliminarData(ActionEvent event) {
        estudiante_ctrl = new EstudianteController();
        StringBuilder dataFormated = new StringBuilder();
        var estudiante = txtNombreConsulta.getText();
        var id_materia = "";

        if (cbxMateriaConsulta.getValue() != null) {
            String materia = cbxMateriaConsulta.getValue().toString().split("-")[0].trim();
            id_materia = materia == "0" || materia.trim() == "" || materia == null ? "" : materia;
        }

        String nombre_estudiante = estudiante.length() == 0 || estudiante.trim() == "" || estudiante == null ? null
                : estudiante;

        List<Estudiante> data = estudiante_ctrl.listByEstudianteAndMateria(nombre_estudiante, id_materia);
        if (data.size() > 0) {
            for (var e : data) {
                dataFormated.append(
                        e.getNombre() + " " + Double.parseDouble(String.valueOf(e.getGenero().getIdentificador())) + " "
                                + Double.parseDouble(String.valueOf(e.getMateria().getIdentificador())) + " "
                                + e.getNota() + "\n");
                if (nombre_estudiante != null && id_materia != "")
                    estudiante_ctrl.delete(e, "ambos");
                else if (nombre_estudiante == null && id_materia != "")
                    estudiante_ctrl.delete(e, "materia");
                else if (nombre_estudiante != null && id_materia == "")
                    estudiante_ctrl.delete(e, "nombre");
            }
            this.MsgDialog("Los siguientes datos han sido eliminados: \n" + dataFormated, "info");
        } else {
            this.MsgDialog("No existen datos con los criterios ingresados", "advertencia");
        }
    }

    @FXML
    void guardarData(ActionEvent event) {
        try {
            genero_ctrl = new GeneroController();
            materia_ctrl = new MateriaController();
            estudiante_ctrl = new EstudianteController();
            var nombre = txtNombre.getText();
            var nota = Double.parseDouble(txtNota.getText());
            var materia = Integer.parseInt(cbxMateria.getValue().toString().split("-")[0].trim());
            var genero = Integer.parseInt(cbxGenero.getValue().toString().split("-")[0].trim());
            if (nota <= 5.0) {
                estudiante = new Estudiante(nombre, genero_ctrl.search(genero), materia_ctrl.search(materia), nota);
                System.out.println(estudiante);
                var flag = estudiante_ctrl.create(estudiante);
                if (flag == 1) {
                    this.MsgDialog("Registro creado exitosamente", "info");
                } else {
                    this.MsgDialog("No se pudo crear el registro, verifique la información digitada", "advertencia");
                }
            } else {
                this.MsgDialog("Por favor verifique la nota que esta tratando de incluir, no cumple con los estandares",
                        "error");
            }
        } catch (Exception e) {
            this.MsgDialog("Error al intentar guardar información " + e.getMessage(), "error");
        }
    }

    // Methods

    void initComboBoxes() {
        genero_ctrl = new GeneroController();
        materia_ctrl = new MateriaController();
        cbxMateria.setItems(FXCollections.observableArrayList(materia_ctrl.list()));
        cbxGenero.setItems(FXCollections.observableArrayList(genero_ctrl.list()));
        cbxMateriaConsulta.setItems(FXCollections.observableArrayList(materia_ctrl.list()));
    }

    void initTxtInput() {
        estudiante_ctrl = new EstudianteController();
        StringBuilder dataFormated = new StringBuilder();
        if (estudiante_ctrl.list().size() > 0) {
            for (var e : estudiante_ctrl.list()) {
                dataFormated.append(
                        e.getNombre() + " " + Double.parseDouble(String.valueOf(e.getGenero().getIdentificador())) + " "
                                + Double.parseDouble(String.valueOf(e.getMateria().getIdentificador())) + " "
                                + e.getNota() + "\n");
            }
            txtInput.setText(dataFormated.toString());
        }
    }

    public List<List<Double>> loadData(String[] data) {

        List<List<Double>> data_cast = new LinkedList<List<Double>>();

        for (var i = 0; i < data.length; i++) {
            String[] aux = data[i].trim().split(" ");
            List<Double> double_cast = new LinkedList<Double>();
            for (String cadena : aux) {
                double_cast.add(Double.parseDouble(cadena));
            }
            data_cast.add(double_cast);
        }

        return data_cast;
    }

    String loadDataOutput(List<List<Double>> data_cast) {

        StringBuilder ouput = new StringBuilder("");
        GradingSystem r = new GradingSystem();

        var genres = r.getCleanDataByCriteria(data_cast, "genres");
        var courses = r.getCleanDataByCriteria(data_cast, "courses");
        var scores = r.getRawData(data_cast, "scores");

        System.out.println(String.format("%.2f", r.stat1(scores)).replace(",", "."));
        ouput.append(String.format("%.2f", r.stat1(scores)).replace(",", ".") + "\n");

        List<Double> data_genre_course = r.getScoreByCriteria("genres", genres, data_cast, "courses").get(1.0);
        List<Double> data_genre_scores = r.getScoreByCriteria("genres", genres, data_cast, "scores").get(0.0);

        if (data_genre_course != null && data_genre_scores != null) {
            System.out.println(r.stat3(data_genre_course, data_genre_scores));
            ouput.append(r.stat3(data_genre_course, data_genre_scores) + "\n");
        } else {
            System.out.println("Worst Course By Genre No calculated");
            ouput.append("Worst Course By Genre No calculated \n");
        }

        List<Double> data_course_student = r.getScoreByCriteria("courses", courses, data_cast, "students").get(1.0);
        List<Double> data_course_score = r.getScoreByCriteria("courses", courses, data_cast, "scores").get(1.0);

        if (data_genre_course != null && data_genre_scores != null) {
            System.out.println(r.stat4(data_course_student, data_course_score) + "\n");
            ouput.append(r.stat4(data_course_student, data_course_score) + "\n");
        } else {
            System.out.println("Best Performance By Course No calculated");
            ouput.append("Best Performance By Course No calculated \n");
        }

        return ouput.toString();
    }

    void MsgDialog(String msg, String type) {
        Alert alert = null;
        String title = "";
        switch (type) {
            case "error":
                alert = new Alert(Alert.AlertType.ERROR);
                title = "¡Ups ha ocurrido un error!";
                break;
            case "info":
                alert = new Alert(Alert.AlertType.INFORMATION);
                title = "¡Información!";
                break;
            case "advertencia":
                alert = new Alert(Alert.AlertType.WARNING);
                title = "Advertencia";
                break;
            default:
                alert = new Alert(Alert.AlertType.INFORMATION);
                title = "¡Información!";
                break;
        }
        if (alert != null) {
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(msg);
            alert.showAndWait();
        } else {
            System.out.println("Envie un tipo de mensaje valido");
        }

    }

}
