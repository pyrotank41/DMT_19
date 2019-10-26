import javafx.application.Application;
import javafx.scene.control.TextArea;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class ProjectNext extends Application {
    //Initialization of tabs
    private static final TabPane TABPANE = new TabPane();
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        TABPANE.getTabs().add(createHome());
        Tab testTab = new Tab();
        testTab.setClosable(true);
        testTab.setContent(btn);
        testTab.setText("Test");
        TABPANE.getTabs().add(testTab);
        StackPane root = new StackPane();
        root.getChildren().add(TABPANE);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static Tab createHome()
    {
        final Tab       homeTab         = new Tab();       // Initialization of home tab   
        final SplitPane homeScreen      = new SplitPane(); // Initialization of home screen Tab
        final TextArea  searchBox       = new TextArea();  // Initialization of input searchBox area
        final VBox      searchResultBox = new VBox();      // Initialization of search result area
        
        homeTab.setText("Main Screen");
        homeTab.setClosable(false); 
        
        searchBox.setMaxHeight(50);
        searchBox.prefWidthProperty().bind(homeScreen.widthProperty());
        searchBox.prefHeightProperty().bind(homeScreen.heightProperty());
        searchBox.setEditable(true);
        searchBox.setStyle("-fx-focus-color: transparent; -fx-control-inner-background:#000000; -fx-font-family: Consolas; -fx-highlight-fill: #00ff00; -fx-highlight-text-fill: #000000; -fx-text-fill: #00ffff");
        
        Button testButton = propertyResult();
        testButton.prefHeightProperty().bind(searchResultBox.heightProperty());
        testButton.prefWidthProperty().bind(searchResultBox.widthProperty());
        searchResultBox.getChildren().add(testButton);
        
        
        homeScreen.getItems().addAll(searchBox, searchResultBox); // stores items in the home Screen
        homeTab.setContent(homeScreen);                           // stores items into home screen tab
        return homeTab;
    }
    
    
    public static Button propertyResult()
    {
        Button propertyResult = new Button(); // Initializtion of property result button
        propertyResult.setMaxWidth(100);
        propertyResult.setText("Hello");
        return propertyResult;
    }
    
    
    public static Tab newSearchResult()
    {
        Tab propertyTab = new Tab(); // Initialization of a new property tab
        
        return propertyTab; // Returns the new tab into where function was called
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
