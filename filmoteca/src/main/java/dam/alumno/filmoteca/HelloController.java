// HelloController.java actualizado
package dam.alumno.filmoteca;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.ObservableList;

public class HelloController {

    @FXML
    private TableView<Pelicula> tableView;

    @FXML
    private TableColumn<Pelicula, String> columnTitle;

    @FXML
    private TableColumn<Pelicula, Integer> columnYear;

    @FXML
    private ImageView posterImageView;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Label ratingLabel;

    private final ObservableList<Pelicula> peliculas = DatosFilmoteca.getInstancia().getListaPeliculas();

    @FXML
    public void initialize() {
        columnTitle.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        columnYear.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asObject());

        tableView.setItems(peliculas);

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showMovieDetails(newValue);
            }
        });
    }

    private void showMovieDetails(Pelicula pelicula) {
        if (pelicula != null) {
            descriptionTextArea.setText(pelicula.getDescription());
            ratingLabel.setText("Rating: " + pelicula.getRating());
            posterImageView.setImage(new Image(pelicula.getPoster()));
        }
    }

    @FXML
    public void handleAddMovie() {
        System.out.println("Añadir película: Implementar ventana de formulario");
        // Implementar lógica para añadir película
    }

    @FXML
    public void handleEditMovie() {
        Pelicula selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            System.out.println("Editar película: Implementar ventana de edición");
            // Implementar lógica para editar película
        } else {
            showAlert("Seleccione una película para editar");
        }
    }

    @FXML
    public void handleDeleteMovie() {
        Pelicula selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Está seguro de que desea eliminar esta película?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    peliculas.remove(selected);
                }
            });
        } else {
            showAlert("Seleccione una película para eliminar");
        }
    }

    @FXML
    public void handleExit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Desea cerrar la aplicación?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                System.exit(0);
            }
        });
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
