/*
 * Name    : - PORVIL
 * Roll No : - 2017304
 */
package superstore.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import superstore.Data.AllStores;
import superstore.Data.AllWarehouses;
import superstore.Data.Category;
import superstore.Data.Item;
import superstore.Data.Store;
import superstore.Data.Superuser;
import superstore.Data.Warehouse;
import superstore.Data.Warehouse_Admin;
import superstore.User_Login_Database;

/**
 * FXML Controller class
 *
 * @author PD
 */
public class SuperUserPageController implements Initializable {

    @FXML
    TextField warehouseNameTF;
    @FXML
    Label warehouseIDL;
    @FXML
    Button warehouseAddB;
    @FXML
    TextField storeNameTF;
    @FXML
    Label storeIDL;
    @FXML
    Label warehouseID;
    @FXML
    Label storeID;
    @FXML
    Button storeAddB;
    @FXML
    AnchorPane warehouseAP;
    @FXML
    AnchorPane storeAP;
    @FXML
    Button addW;
    @FXML
    Button addS;
    @FXML
    Button warehouseCancelB;
    @FXML
    Button storeCancelB;
    @FXML
    Button clearB;
    @FXML
    TextArea consoleTA;
    @FXML
    ChoiceBox warehouseCB;
    @FXML
    ChoiceBox storeCB;
    @FXML
    Button linkS;
    @FXML
    Button createWA;
    @FXML
    Button checkWD;
    @FXML
    Button checkSD;
    @FXML
    Button checkWDB;
    @FXML
    Button checkSDB;
    @FXML
    Button checkCancelWDB;
    @FXML
    Button checkCancelSDB;
    @FXML
    Button addWAB;
    @FXML
    Button cancelWAB;
    @FXML
    TextField loginTF;
    @FXML
    TextField passwordTF;
    @FXML
    ChoiceBox warehouseLinkCB;
    @FXML
    AnchorPane warehouseAdminAP;

    private Superuser superuser;
    private AllWarehouses warehouses;
    private AllStores stores;
    private User_Login_Database loginDatabase;

    ArrayList<Integer> test;
    static int count = 1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void initialize(User_Login_Database loginDatabase, AllWarehouses warehouses, AllStores stores, Superuser superuser) {
        System.out.println("LOL in" + getClass().getName());
        test = loginDatabase.test;
        this.loginDatabase = loginDatabase;
        this.superuser = superuser;
        this.warehouses = warehouses;
        this.stores = stores;
        
        
        this.warehouseAP.setVisible(false);
        this.storeAP.setVisible(false);
        this.warehouseID.setVisible(false);
        this.storeID.setVisible(false);
        this.warehouseCB.setVisible(false);
        this.storeCB.setVisible(false);
        this.checkWDB.setVisible(false);
        this.checkSDB.setVisible(false);
        this.checkCancelWDB.setVisible(false);
        this.checkCancelSDB.setVisible(false);
        this.warehouseAdminAP.setVisible(false);

        refresh();

        this.warehouseCB.getSelectionModel().selectedItemProperty().addListener((e, oldvalue, newvalue) -> {

            System.out.println(newvalue);
            int index = this.warehouseCB.getSelectionModel().getSelectedIndex();
            if (this.warehouseCB.getSelectionModel().getSelectedIndex() >= 0) {
                if (index >= 0) {
                    String s = "Selected Warehouse :- \nName :- " + this.warehouses.getAllwarehouses().get(index).getName() + "\nID :- "
                            + this.warehouses.getAllwarehouses().get(index).getID();
                    this.consoleTA.setText(s);
                }
            }
        });

        this.storeCB.getSelectionModel().selectedItemProperty().addListener((e, oldvalue, newvalue) -> {

            System.out.println(newvalue);
            int index = this.storeCB.getSelectionModel().getSelectedIndex();
            if (this.storeCB.getSelectionModel().getSelectedIndex() >= 0) {
                if (index >= 0) {
                    String s = "Selected Store :- \nName :- " + this.stores.getAllstores().get(index).getName() + "\nID :- "
                            + this.stores.getAllstores().get(index).getID();
                    this.consoleTA.setText(s);
                }
            }
        });

        this.warehouseLinkCB.getSelectionModel().selectedItemProperty().addListener((e, oldvalue, newvalue) -> {

            System.out.println(newvalue);
            int index = this.warehouseLinkCB.getSelectionModel().getSelectedIndex();
            if (this.warehouseLinkCB.getSelectionModel().getSelectedIndex() >= 0) {
                if (index >= 0) {
                    String s = "Selected Warehouse :- \nName :- " + this.warehouses.getAllwarehouses().get(index).getName() + "\nID :- "
                            + this.warehouses.getAllwarehouses().get(index).getID();
                    this.consoleTA.setText(s);
                }
            }
        });

    }

    void refresh() {
        this.warehouseCB.getItems().clear();
        String temp1;
        for (int i = 0; i < warehouses.getAllwarehouses().size(); i++) {
            temp1 = warehouses.getAllwarehouses().get(i).getName();
            System.out.println(temp1);
            this.warehouseCB.getItems().add(temp1);
        }

        this.storeCB.getItems().clear();
        String temp2;
        for (int i = 0; i < stores.getAllstores().size(); i++) {
            temp2 = stores.getAllstores().get(i).getName();
            System.out.println(temp2);
            this.storeCB.getItems().add(temp2);
        }

        this.warehouseLinkCB.getItems().clear();
        String temp3;
        for (int i = 0; i < warehouses.getAllwarehouses().size(); i++) {
            temp3 = warehouses.getAllwarehouses().get(i).getName();
            System.out.println(temp3);
            if (this.warehouses.getAllwarehouses().get(i).getAdmin() == null) {
                this.warehouseLinkCB.getItems().add(temp3);
            }
        }

    }

    public void add() {
        test.add(count++);
    }

    public void print() {
        System.out.println("in superuser:-" + test);
    }

    public void addWarehouse() {
        this.addW.setDisable(true);
        this.warehouseAP.setVisible(true);
        this.warehouseIDL.setText(this.warehouseIDL.getText().trim() + " " + this.warehouses.getWarehouseID());

        //superuser.createWarehouse();
        System.out.println("CREATING WAREHOUSE ");

    }

    public void addStore() {
        this.addS.setDisable(true);
        this.storeAP.setVisible(true);
        this.storeIDL.setText(this.storeIDL.getText().trim() + " " + this.stores.getStoreID());

        //superuser.createStore();
        System.out.println("CREATING STORE");
    }

    public void linkStoreToWarehouse() {
        superuser.assignWarehouse();
        System.out.println("LINKING STORE TO WAREHOUSE");
    }

    public void showWarehouseData() {

        this.checkWD.setDisable(true);
        this.warehouseCB.setVisible(true);
        this.warehouseID.setVisible(true);
        //this.warehouseAddB.setVisible(true);
        this.checkCancelWDB.setVisible(true);
        this.checkWDB.setVisible(true);

        //superuser.display(new Warehouse());
        System.out.println("DISPLAYING WAREHOUSE ___  DATA");
    }

    public void showWD() throws IOException {

        int index = this.warehouseCB.getSelectionModel().getSelectedIndex();

        if (index >= 0) {

            //DELETE BELOW CODE
            Item temp = new Item();
            temp.setName("Test@DISPLAYDATA");
            temp.setPrice(99.52);
            temp.setQuantity(12);

            Item temp1 = new Item();
            temp1.setName("Test@DISPLAYDATA2");
            temp1.setPrice(691.752);
            temp1.setQuantity(102);

            this.warehouses.getAllwarehouses().get(index).addCategory("test@category1");
            Category c = this.warehouses.getAllwarehouses().get(index).getCategories().get(0);

            this.warehouses.getAllwarehouses().get(index).addSub_Category("test@subcategory1", c);
            this.warehouses.getAllwarehouses().get(index).addItem(this.warehouses.getAllwarehouses().get(index).getCategories().get(0), this.warehouses.getAllwarehouses().get(index).getCategories().get(0).getSubcategories().get(0), temp);
            this.warehouses.getAllwarehouses().get(index).addItem(this.warehouses.getAllwarehouses().get(index).getCategories().get(0), this.warehouses.getAllwarehouses().get(index).getCategories().get(0).getSubcategories().get(0), temp1);
            //TILL HERE

            FXMLLoader loader = new FXMLLoader(getClass().getResource("WarehouseDataDisplay.fxml"));
            Parent root = (Pane) loader.load();
            Scene scene;
            Stage stage;
            loader.<WarehouseDataDisplayController>getController().initialize(this.warehouses.getAllwarehouses().get(index),1);//FIX this using choichbox and complete it
            scene = new Scene(root, 600, 600);
            stage = new Stage();

            stage.setTitle("SuperStore Management");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            this.warehouseCB.setVisible(false);
            this.warehouseID.setVisible(false);
            //this.warehouseAddB.setVisible(false);
            this.warehouseCancelB.setVisible(false);
            this.checkCancelWDB.setVisible(false);
            this.checkWDB.setVisible(false);

            this.checkWD.setDisable(false);
            this.consoleTA.setText("");
        } else {
            this.consoleTA.setText("SELECT A WAREHOUSE FIRST");
        }

    }

    public void showSD() throws IOException {
        int index = this.storeCB.getSelectionModel().getSelectedIndex();

        if (index >= 0) {

            //DELETE BELOW CODE
            Item temp = new Item();
            temp.setName("STest@DISPLAYDATA");
            temp.setPrice(99.52);
            temp.setQuantity(12);

            Item temp1 = new Item();
            temp1.setName("STest@DISPLAYDATA2");
            temp1.setPrice(691.752);
            temp1.setQuantity(102);

            this.stores.getAllstores().get(index).addCategory("test@category1");
            Category c = this.stores.getAllstores().get(index).getCategories().get(0);

            this.stores.getAllstores().get(index).addSub_Category("test@subcategory1", c);
            this.stores.getAllstores().get(index).addItem(this.stores.getAllstores().get(index).getCategories().get(0), this.stores.getAllstores().get(index).getCategories().get(0).getSubcategories().get(0), temp);
            this.stores.getAllstores().get(index).addItem(this.stores.getAllstores().get(index).getCategories().get(0), this.stores.getAllstores().get(index).getCategories().get(0).getSubcategories().get(0), temp1);
            //TILL HERE

            FXMLLoader loader = new FXMLLoader(getClass().getResource("WarehouseDataDisplay.fxml"));
            Parent root = (Pane) loader.load();
            Scene scene;
            Stage stage;
            loader.<WarehouseDataDisplayController>getController().initialize(this.stores.getAllstores().get(index),2);//FIX this using choichbox and complete it
            scene = new Scene(root, 600, 600);
            stage = new Stage();

            stage.setTitle("SuperStore Management");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            this.warehouseCB.setVisible(false);
            this.warehouseID.setVisible(false);
            //this.warehouseAddB.setVisible(false);
            this.warehouseCancelB.setVisible(false);
            this.checkCancelWDB.setVisible(false);
            this.checkWDB.setVisible(false);

            this.checkWD.setDisable(false);
            this.consoleTA.setText("");
        } else {
            this.consoleTA.setText("SELECT A WAREHOUSE FIRST");
        }

    }

    public void cancelWDB() {
        this.checkWD.setDisable(false);
        this.warehouseCB.setVisible(false);
        this.warehouseID.setVisible(false);
        //this.warehouseAddB.setVisible(false);
        this.checkCancelWDB.setVisible(false);
        this.checkWDB.setVisible(false);
    }

    public void cancelSDB() {
        this.checkSD.setDisable(false);
        this.storeCB.setVisible(false);
        this.storeID.setVisible(false);
        //this.warehouseAddB.setVisible(false);
        this.checkCancelSDB.setVisible(false);
        this.checkSDB.setVisible(false);
    }

    public void showStoreData() {
        this.checkSD.setDisable(true);
        this.storeCB.setVisible(true);
        this.storeID.setVisible(true);
        //this.warehouseAddB.setVisible(true);
        this.checkCancelSDB.setVisible(true);
        this.checkSDB.setVisible(true);

        //superuser.display(new Store(1, "noob"));
        System.out.println("DISPLAYING STORE ____ DATA");
    }

    public void addWarehouseAdd() {
        int id = this.warehouses.getWarehouseID();
        String name = this.warehouseNameTF.getText().trim();

        if (!name.isEmpty()) {
            Warehouse temp = new Warehouse(id, name);
            this.warehouses.getAllwarehouses().add(temp);
            this.warehouses.incrementWarehouseID();

            System.out.println("WAREHOUSE CREATED");
            System.out.println("NAME :- " + name);
            System.out.println("ID   :- " + id);
            System.out.println("END");

            //in console
            String s = "WAREHOUSE CREATED\nNAME :- " + name + "\nID   :- " + id + "\nDONE";
            this.consoleTA.setText(s);

            this.warehouseAP.setVisible(false);
            this.addW.setDisable(false);

            this.warehouseIDL.setText("Warehouse ID :- ");
            this.warehouseNameTF.setText("");

            refresh();
        } else {

            //in console
            String s = "YOU MUST ENTER WAREHOUSE NAME";
            this.consoleTA.setText(s);
        }

    }

    public void addStoreAdd() {
        int id = this.stores.getStoreID();
        String name = this.storeNameTF.getText().trim();

        if (!name.isEmpty()) {
            Store temp = new Store(id, name);
            this.stores.getAllstores().add(temp);
            this.stores.incrementStoreID();

            System.out.println("STORE CREATED");
            System.out.println("NAME :- " + name);
            System.out.println("ID   :- " + id);
            System.out.println("END");

            //in console
            String s = "STORE CREATED\nNAME :- " + name + "\nID   :- " + id + "\nDONE";
            this.consoleTA.setText(s);

            this.storeAP.setVisible(false);
            this.addS.setDisable(false);

            this.storeIDL.setText("Store ID :- ");
            this.storeNameTF.setText("");

            refresh();
        } else {

            //in console
            String s = "YOU MUST ENTER STORE NAME";
            this.consoleTA.setText(s);
        }

    }

    public void cancelWarehouseAdd() {
        this.warehouseAP.setVisible(false);
        this.addW.setDisable(false);
        this.warehouseIDL.setText("Warehouse ID :- ");
        this.warehouseNameTF.setText("");
        this.consoleTA.setText("");
    }

    public void cancelStoreAdd() {
        this.storeAP.setVisible(false);
        this.addS.setDisable(false);
        this.storeIDL.setText("Store ID :- ");
        this.storeNameTF.setText("");
        this.consoleTA.setText("");
    }

    public void clear() {
        this.consoleTA.setText("");
    }

    public void addWALink() {
        String id = this.loginTF.getText().trim();
        String pswd = this.passwordTF.getText().trim();

        int index = this.warehouseLinkCB.getSelectionModel().getSelectedIndex();

        if (!id.isEmpty() && !pswd.isEmpty() && pswd.length() >= 8) {
            if (index >= 0) {
                String s = this.warehouseLinkCB.getSelectionModel().getSelectedItem().toString();//fix this, so that we can get selected itme name and find warehouse
                System.out.println(s);
                this.consoleTA.setText(s);
                Warehouse w = new Warehouse(999999, s);
                int index2 = this.warehouses.getAllwarehouses().indexOf(w);
                Warehouse_Admin temp = new Warehouse_Admin(id, pswd, this.warehouses.getAllwarehouses().get(index2));
                System.out.println("inside 2 if conditions");
                this.consoleTA.setText("inside 2 if conditions");
                this.loginDatabase.getWarehouseDatabase().put(id, temp);//changed here
                //TILL YESTERDAY NIGHT

                System.out.println("TEST");
                
                this.warehouseAdminAP.setVisible(false);
                this.createWA.setDisable(false);
                this.loginTF.setText("");
                this.passwordTF.setText("");

            } else {
                this.consoleTA.setText("SELECT A WAREHOUSE");
            }
        } else {
            this.consoleTA.setText("Enter All The Details");
        }

    }

    public void cancelWALink() {
        this.warehouseAdminAP.setVisible(false);
        this.createWA.setDisable(false);
        this.loginTF.setText("");
        this.passwordTF.setText("");
    }

    public void createWarehouseAdmin() {
        this.warehouseAdminAP.setVisible(true);
        this.createWA.setDisable(true);
    }

}
