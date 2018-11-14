/*
 * Name    : - PORVIL
 * Roll No : - 2017304
 */
package superstore.FXML;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import superstore.Data.Item;
import superstore.Data.Store;
import superstore.Data.Warehouse;

/**
 * FXML Controller class
 *
 * @author PD
 */
public class WarehouseDataDisplayController implements Initializable {

    @FXML
    TableView dataTV;
    @FXML
    Button sortB;
    
    private Warehouse warehouse;
    private Store store;
    private int type; //1-WAREHOUSE , 2-STORE
    private int sortPriceType=1;//1-asc , 2-desc
    
    TableColumn<Item,Double> priceColumn = new TableColumn<>("Price");
        
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void initialize(Warehouse warehouse,int type) {
        this.warehouse = warehouse;
        this.type = type;
        //STORE IS NULL
        priceColumn.sortTypeProperty().setValue(TableColumn.SortType.ASCENDING);
        setup();
        
        
    }  
    
    public void initialize(Store store,int type) {
        this.store = store;
        this.type = type;
        //WAREHOUSE IS NULL
        setup();        
        
        priceColumn.sortTypeProperty().setValue(TableColumn.SortType.ASCENDING);
    }  
    
    public void setup(){
        TableColumn<Item,String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<Item,Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(80);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        //
        if(type == 1)
            dataTV.setItems(getItems());
        else if(type == 2)
            dataTV.setItems(getItems2());
        else{
            System.out.println("WOW, CHECK HERE");
        }
        dataTV.getColumns().addAll(nameColumn,quantityColumn,priceColumn);
        priceColumn.setSortType(TableColumn.SortType.ASCENDING);//check sorting stuff
        dataTV.getSortOrder().setAll(priceColumn,quantityColumn);
        
    } 
    
    public ObservableList<Item> getItems(){
        ObservableList<Item>  items = FXCollections.observableArrayList();
        for (int i = 0; i < warehouse.getItems().size(); i++) {
            items.add(warehouse.getItems().get(i));
            System.out.println(warehouse.getItems().get(i).getName());
        }
        
        return items;
    }
    
    public ObservableList<Item> getItems2(){
        ObservableList<Item>  items = FXCollections.observableArrayList();
        for (int i = 0; i < store.getItems().size(); i++) {
            items.add(store.getItems().get(i));
        }
        
        return items;
    }
    
    public void sort(){
//        dataTV.sort
        System.out.println("WTH - " + priceColumn.getSortType().name());
//        dataTV.setSortType;
        if(this.sortPriceType == 1 ){
            System.out.println("before - ascending\nNow - descending");
            priceColumn.sortTypeProperty().setValue(TableColumn.SortType.DESCENDING);
            this.sortPriceType = 2;
            this.sortB.setText("Price - Sort Ascending");
        
        }
        else if(this.sortPriceType == 2 ){
            System.out.println("before - descending\nNow - ascending");
            priceColumn.sortTypeProperty().setValue(TableColumn.SortType.ASCENDING);
            this.sortPriceType = 1;
            this.sortB.setText("Price - Sort Descending");
        }
        dataTV.sort();
        
        System.out.println("sorting\n\n");
    }
    
}
