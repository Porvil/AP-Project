/*
 * Name    : - PORVIL
 * Roll No : - 2017304
 */
package superstore.FXML.Store;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import superstore.Data.Category;
import superstore.Data.Item;
import superstore.Data.Sub_Category;
import superstore.Data.Store;

/**
 * FXML Controller class
 *
 * @author PD
 */
public class AddController implements Initializable {
    
    @FXML
    ChoiceBox mainCB;
    @FXML
    ChoiceBox catCB;
    @FXML
    ChoiceBox subCB;
    @FXML
    TextField TF;
    @FXML
    TextField quantityTF;
    @FXML
    TextField priceTF;
    @FXML
    TextField hTF;
    @FXML
    TextField uidTF;
    @FXML
    TextField kTF;
    @FXML
    Button addItem;
    @FXML
    Button addcommon;
    @FXML
    AnchorPane ap2;
    
    private Store store;
    private int type;//0-cat , 1-sub , 2-sub
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
     *
     * @param store
     */
    public void initialize(Store store) {
        
        this.store = store;
        
        //HIDE by default
        reset();
        this.addcommon.setDisable(true);
        
        
        this.mainCB.setTooltip(new Tooltip("Select Main Option"));
        this.catCB.setTooltip(new Tooltip("Select Category"));
        this.subCB.setTooltip(new Tooltip("Select Sub-Category"));
        
        //ADDING DEFAULT OPTIONS - ADD - cat,sub,item
        this.mainCB.getItems().addAll("Add Category","Add Sub-Category","Add Item");
        
        this.mainCB.getSelectionModel().selectedItemProperty().addListener(  (e,oldvalue,newvalue) -> {
            
            System.out.println(newvalue);
            reset();
            
            if(this.mainCB.getSelectionModel().getSelectedIndex() >= 0){
                this.type = this.mainCB.getSelectionModel().getSelectedIndex() ;
                
                switch(this.type){
                    case 0:
                        //add cat
                        this.TF.setPromptText("Enter Category Name");
                        break;
                    case 1:
                        //add sub
                        //populate categories
                        this.catCB.getItems().clear();
                        for (int i = 0; i < this.store.getCategories().size(); i++) {
                            this.catCB.getItems().add(this.store.getCategories().get(i).getName());
                        }
                        
                        this.TF.setPromptText("Enter Sub-Category Name");
                        this.catCB.setVisible(true);
                        break;
                    case 2:
                        //add item
                        //populate categories
                        this.catCB.getItems().clear();
                        for (int i = 0; i < this.store.getCategories().size(); i++) {
                            this.catCB.getItems().add(this.store.getCategories().get(i).getName());
                        }
                                                
                        this.TF.setPromptText("Enter Item Name");
                        this.catCB.setVisible(true);
                        this.subCB.setVisible(true);
                        break;
                    default:
                        System.out.println("DEFAULT");
                        break;
                }
            }
            
        });
        
        this.catCB.getSelectionModel().selectedItemProperty().addListener(  (e,oldvalue,newvalue) -> {
            
            System.out.println(newvalue);
//            reset();
            
            int index = this.catCB.getSelectionModel().getSelectedIndex();
            
            if(index >= 0){
                this.subCB.getItems().clear();
                for (int i = 0; i < this.store.getCategories().get(index).getSubcategories().size(); i++) {
                    this.subCB.getItems().add(this.store.getCategories().get(index).getSubcategories().get(i).getName());
                }
            }
            
        });
        
        
    }    
    
    /**
     *
     */
    public void add1(){
        System.out.println("type--> "+ type);
        String s = "";
        Category c = null;
        switch(this.type){
            case 0:
                s = this.TF.getText().trim();
                c = new Category(s);
                this.store.getCategories().add(c);
                System.out.println("category added -> " + s);

                this.addcommon.setDisable(true);
                this.TF.setText("");
                reset();
                
                break;
            
            case 1:
                int index = this.catCB.getSelectionModel().getSelectedIndex();
                s = this.TF.getText().trim();
                c = this.store.getCategories().get(index);
                this.store.addSub_Category(s, c);
                System.out.println("subcategory added -> " + s + " at --> " + c.getName());

                this.addcommon.setDisable(true);
                this.TF.setText("");
                reset();
                this.catCB.setVisible(true);
                
                break;
            default:
                System.out.println("WTH noob");
                break;
        }
        
        
    }
    
    /**
     *
     */
    public void reset(){
        this.ap2.setVisible(false);
        this.catCB.setVisible(false);
        this.subCB.setVisible(false);
        this.TF.setText("");
        this.TF.setPromptText("");
    }
    
    /**
     *
     */
    public void check(){
        System.out.println("INSIDE CHECK");
        System.out.println("type:- " + type);
        
        switch(this.type){
            case 0:
                //add cat
                cat();
                break;
            case 1:
                //add sub
                sub();
                break;
            case 2:
                //add item
                item();
                break;
            default:
                System.out.println("DEFAULT");
                break;
        }
        
    }
    
    /**
     *
     */
    public void cat(){
        
        String name = TF.getText().trim();
        if (TF.getText() != null && (!TF.getText().isEmpty() || !TF.getText().trim().isEmpty())) {
            if(checkIfCategoryExists(name)){
                //category exists
                this.addcommon.setDisable(true);
            }
            else{
                this.addcommon.setDisable(false);
            }
        }
        else{
            this.addcommon.setDisable(true);
        }
    }
    
    /**
     *
     */
    public void sub(){
        
        String name = TF.getText().trim();
        if (TF.getText() != null && (!TF.getText().isEmpty() || !TF.getText().trim().isEmpty())) {
            if(checkIfSubCategoryExists(name)){
                //subcategory exists
                this.addcommon.setDisable(true);
            }
            else{
                this.addcommon.setDisable(false);
            }
        }
        else{
            this.addcommon.setDisable(true);
        }
        
    }
    
    /**
     *
     */
    public void item(){
        
        String name = TF.getText().trim();
        this.addcommon.setDisable(true);
        if (TF.getText() != null && (!TF.getText().isEmpty() || !TF.getText().trim().isEmpty())) {
            if(checkIfItemExists(name)){
                //item exists
                this.ap2.setVisible(false);
                this.uidTF.setText("");
            }
            else{
                this.ap2.setVisible(true);
                this.uidTF.setText("UID :- " + this.store.getUID());
            }
        }
        else{
            this.ap2.setVisible(false);
        }
        
    }
    
    /**
     *
     */
    public void clickOnAdd(){
        System.out.println("inside click on add");
        
        Item itemTemp = new Item();
        String name = this.TF.getText().trim();
        double price = Double.parseDouble(this.priceTF.getText().trim());
        int quantity = Integer.parseInt(this.quantityTF.getText().trim());
        int h = Integer.parseInt(this.hTF.getText().trim());
        int k = Integer.parseInt(this.kTF.getText().trim());
        
        itemTemp.setName(name);
        itemTemp.setH(h);
        itemTemp.setK(k);
        itemTemp.setPrice(price);
        itemTemp.setQuantity(quantity);
        
        int index = this.catCB.getSelectionModel().getSelectedIndex();
        int index1 = this.subCB.getSelectionModel().getSelectedIndex();
        
        if(index>=0 && index1>=0){
            String category = this.store.getCategories().get(index).getName();
            String subcategory = this.store.getCategories().get(index).getSubcategories().get(index1).getName();

            itemTemp.setPath(category + ">" + subcategory);
            itemTemp.setEOQ(this.store.getD());
            itemTemp.setUID(this.store.getUID());
            this.store.increamentUID();
            this.store.addItem(this.store.getCategories().get(index), this.store.getCategories().get(index).getSubcategories().get(index1),itemTemp);
            System.out.println(itemTemp);
            System.out.println("ITEM ADDED");
            
            //reseting values
            this.hTF.setText("");
            this.kTF.setText("");
            this.priceTF.setText("");
            this.quantityTF.setText("");
           
        }
        else{
            System.out.println("EITHER CATEGORY OR SUBCATEGORY ISNT CHOOSEN");
        }
        check();
//        System.out.println(itemTemp);

    }
    
    private boolean checkIfItemExists(String name){
        //returns true if item exists,else false
        
        ArrayList<Item> items = null;
        int index = this.catCB.getSelectionModel().getSelectedIndex();
        int index1 = this.subCB.getSelectionModel().getSelectedIndex();
        
        System.out.println("index:- " + index1 + "       INSIDE checkIfItemExists Method.");
        if(index1 >=0 && index >=0){
            items = this.store.getCategories().get(index).getSubcategories().get(index1).getItems();
        }
        
        if(items == null){
            return false;
        }
        else{
            for (int i = 0; i < items.size(); i++) {
                if(items.get(i).getName().equals(name))
                    return true;
            }
        }
        return false;        
    }
    
    private boolean checkIfCategoryExists(String name){
        //returns true if category exists,else false
        
        ArrayList<Category> cat = null;

        cat = this.store.getCategories();
        
        
        if(cat == null){
            return false;
        }
        
        else{
            for (int i = 0; i < cat.size(); i++) {
                if(cat.get(i).getName().equals(name))
                    return true;
            }
        }
        return false;        
    }
    
    private boolean checkIfSubCategoryExists(String name){
        //returns true if subcategory exists,else false
        
        ArrayList<Sub_Category> sub = null;
        int index = this.catCB.getSelectionModel().getSelectedIndex();
        
        if(index >=0){
            sub = this.store.getCategories().get(index).getSubcategories();
        }
        
        if(sub == null){
            return false;
        }
        else{
            for (int i = 0; i < sub.size(); i++) {
                if(sub.get(i).getName().equals(name))
                    return true;
            }
        }
        return false;        
    }
    
}
