//Insert package
package sample; 
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import sampel.Search;

public class Main extends Application{
    //Create table view
    private TableView<Search> tableView = new TableView<sampel.Search>();
    //Create data to show in table view 
    private final ObservableList<Search> data
            = FXCollections.observableArrayList(
            new Search("Ambar", "Pensil", 10000), //Create instantiation from Search class
            new Search("Ambar", "Pulpen", 15000),
            new Search("Ambar", "Buku", 20000),
            new Search("Ambar", "Kertas", 5000),
            new Search("Ekstensi", "Kursi", 200000),
            new Search("Ekstensi", "Meja", 500000),
            new Search("Ekstensi", "Lemari", 2000000),
            new Search("Ekstensi", "Kulkas", 5500000),
            new Search("Ekstensi", "AC", 5000000),
            new Search("Ekstensi", "Televisi", 4000000)
    );

     @Override
    public void start(Stage primaryStage) throws Exception{
        //Create gridpane and setting the padding
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        
        //Create textfield 
        TextField namefield = new TextField ();
        namefield.setPromptText("Masukkan nama barang");
        namefield.setPrefColumnCount(10);
        namefield.getText();

        //Set text field in axis x=0 and y=0
        GridPane.setConstraints(namefield, 0, 0);
        
        //Insert image for the button
        Image image = new Image(getClass().getResourceAsStream("loupe.png"));
        
        //Create button with text "Cari" and image which inserted before
        Button btn = new Button("Cari", new ImageView (image));
        
        //Set button in axis x=1 and y=0
        GridPane.setConstraints(btn, 1, 0);
        
        //Add textfield and button to the hbox container
        HBox hBox = new HBox(namefield, btn);
        hBox.setAlignment(Pos.TOP_LEFT);
        namefield.setMinWidth(200);
        
        //Create column name with "No" and filled in with autonumbering value
        TableColumn column0 = new TableColumn( "No" );
        column0.setCellFactory( new Callback<TableColumn, TableCell>()
        {
            @Override
            public TableCell call(TableColumn p)
            {
                return new TableCell()
                {
                    @Override
                    public void updateItem(Object item, boolean empty)
                    {
                        super.updateItem(item, empty);
                        setGraphic(null);
                        setText(empty ? null : getIndex() + 1 + "");
                    }
                };
            }
        });
        
        //Create column name with "Nama Store" and filled in according with namastore data in Search class
        TableColumn column1 = new TableColumn("Nama Store");
        column1.setCellValueFactory(new PropertyValueFactory<sampel.Search, String>("namastore"));
    
        //Create column name with "Nama Barang" and filled in according with namabarang data in Search class
        TableColumn column2 = new TableColumn("Nama Barang");
        column2.setCellValueFactory(new PropertyValueFactory<sampel.Search, String>("namabarang"));
        
        
        //Create column name with "Harga" and filled in according with harga data in Search class
        TableColumn column3 = new TableColumn("Harga");
        column3.setCellValueFactory(new PropertyValueFactory<sampel.Search, String>("harga"));
        
        //Set the size for each column
        column0.setMinWidth(30);
        column1.setMinWidth(200);
        column2.setMinWidth(200);
        column3.setMinWidth(100);

        column0.setResizable(false);
        column1.setResizable(false);
        column2.setResizable(false);
        column3.setResizable(false);

        //Set button in axis x=0 and y=1
        GridPane.setConstraints(tableView, 0, 1);

        //Pass the data to a filtered list
        FilteredList<sampel.Search> filter = new FilteredList(data, p -> true);
        
        //Set the table's items using the filtered list
        tableView.setItems(filter);
        
        //Add all column to the tableview
        tableView.getColumns().addAll(column0, column1, column2, column3);
        
        //Event handler for button, where button is clicked will perform the event (filter.setpredicate)
        btn.setOnMouseClicked(keyEvent ->
        {
            filter.setPredicate(p -> p.getnamabarang().toLowerCase().contains(namefield.getText().toLowerCase().trim()));
        });
        
        //Get the tableview and hbox to the gridpane container
        grid.getChildren().add(tableView);
        grid.getChildren().add(hBox);
        
        //Set title of window
        primaryStage.setTitle("Cari");
        
        //Set scene size with the node=gridpane, width=600, height=400
        primaryStage.setScene(new Scene(grid, 600, 400));
        
        //Display all the content
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {
        try{
            Application.launch(args);
        } catch(Exception ex){
            System.out.print(ex.getMessage() + "\n");
        }
    }
}
