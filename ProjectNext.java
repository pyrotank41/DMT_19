import javafx.application.Application;
import javafx.scene.control.TextArea;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tabimport javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.RowConstraints;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.text.Text;
import javafx.scene.layout.ColumnConstraints;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.binding.Bindings;


public class ProjectNext extends Application {
    private static final TabPane    TABPANE                        = new TabPane();
    private static final StackPane  ROOT                           = new StackPane();
    private static final VBox       SEARCHRESULTBOX                = new VBox();     
    private static final ScrollPane SEARCHRESULTBOXHOLDER          = new ScrollPane(SEARCHRESULTBOX);
    private static final String     PROPERTYBUTTONBACKGROUNDREG    = "-fx-background-color: #FFBC7A;";
    private static final String     PROPERTYBUTTONBACKGROUNDHOVER  = "-fx-background-color: #DD6F03;";
  
    
    //This function gets called at the start of the program for Initialization 
    //of the default stage.
    @Override
    public void start(Stage primaryStage) 
    {
        //Creates and add the default home tab
        TABPANE.getTabs().add(createHome());
        //Set the background of the tabs menu
        TABPANE.setStyle("-fx-background: #FFFFFF;");
        
        for(int i = 0; i < 50; i++)
        {
            addSpacing();
            propertyResult("Bob" + i, "fasdfasdfasdf", "12312", "20.3" , "Accumsan ut placerat tincidunt metus, "
                    + "sed vel quam mauris nec feugiat a, habitant ligula cras inceptos lacus, nulla ligula dui. "
                    + "Tortor nam magna nulla quis orci, rutrum lobortis congue molestie, ultricies viverra mauris "
                    + "pulvinar mauris lobortis, eget fugiat libero convallis vel, metus quisque. Commodo ut nec sed "
                    + "et fermentum, vitae tincidunt diam fusce, dapibus tincidunt egestas nec quam enim, magna blandit "
                    + "urna. Risus magna porta, gravida placerat viverra sem sociis ullamcorper, mollis lacus mauris, "
                    + "metus proin sapien, amet orci ac scelerisque mi. Vivamus consectetuer nullam, donec ut integer odio, "
                    + "quam nulla. Tempus facilisis do erat et, sodales a, ipsum tristique fusce sed consectetuer in enim. "
                    + "Venenatis enim, lorem cursus cursus vulputate, erat sit dapibus tortor a, gravida vitae maecenas, "
                    + "in eu quisque et justo aliquam. Posuere justo, et metus urna in velit integer, at eleifend. "
                    + "Pellentesque officiis scelerisque odio luctus ac, metus at justo volutpat nibh mauris a. Maecenas "
                    + "nec ligula quis dolor, adipiscing ultricies hac duis morbi, lectus pharetra rutrum a sed, rutrum per, "
                    + "aliquam id nec nulla ut nam blandit. Sed aliquam eget phasellus, nibh scelerisque.");
        }
        //Adds tab panel to ROOT StackPane
        ROOT.getChildren().add(TABPANE);
        //Set the background of the ROOT StackPane
        ROOT.setStyle("-fx-background: #FFA750;");
        
        Scene scene = new Scene(ROOT, 450, 600);
        primaryStage.setTitle("NEXT");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    
    // Creates the default home screen
    public static Tab createHome()
    {
        final Tab        homeTab         = new Tab();       // Initialization of home tab   
        final GridPane   homeScreen      = new GridPane();  // Initialization of home screen Tab
        final TextField  searchBox       = new TextField(); // Initialization of input searchBox area
        
        
        homeTab.setText("Home");
        homeTab.setStyle("-fx-font-size: 14;");
        homeTab.setClosable(false);
        
        // Sets the max height for the textbox as well as the width. Also make it editable
        searchBox.setMaxHeight(50);
        searchBox.prefWidthProperty().bind(homeScreen.widthProperty());
        searchBox.setEditable(true);
        
        // Sets the scroll bars policy as well as asigns content to ScrollPane
        SEARCHRESULTBOXHOLDER.setHbarPolicy(ScrollBarPolicy.NEVER);
        SEARCHRESULTBOXHOLDER.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        SEARCHRESULTBOXHOLDER.setFitToWidth(true);
        SEARCHRESULTBOXHOLDER.setContent(SEARCHRESULTBOX);
        SEARCHRESULTBOXHOLDER.setStyle("-fx-background: #FF9328;");
        
        // Sets the VBox width and Height related to the outer box containing the SEARCHRESULTBOX
        SEARCHRESULTBOX.prefWidthProperty().bind(homeScreen.widthProperty());
        SEARCHRESULTBOX.prefHeightProperty().bind(homeScreen.heightProperty());
        SEARCHRESULTBOX.setAlignment(Pos.CENTER);
        
        // Adds items to the homescreen as well as blinds the homescreen to the width 
        // of the ROOT width property.
        homeScreen.prefWidthProperty().bind(ROOT.widthProperty());
        homeScreen.addRow(0, searchBox);
        homeScreen.addRow(1, SEARCHRESULTBOXHOLDER);
        
        // Places the homescreen within the Home Tab
        homeTab.setContent(homeScreen);                           // stores items into home screen tab
        return homeTab;
    }
    
    // Creates a small spacing in the vertical
    public static void addSpacing()
    {
        Text space = new Text();
        space.setStyle("-fx-font-size: 4;");
        SEARCHRESULTBOX.getChildren().add(space);
    }
    
    // Creates a button to show up on the main page to be clicked on
    public static void propertyResult(String name, String address, String ID, String price, String information)
    {
        GridPane propertyResult = new GridPane();
        Text propertyName       = new Text(name);
        Text propertyAddresse   = new Text(address);
        Text propertyID         = new Text(ID);
        Text propertyPrice      = new Text("$" + price);
        
        // formats each text property item
        propertyName.setStyle("-fx-font-size: 16;");
        propertyAddresse.setStyle("-fx-font-size: 12; ");
        propertyID.setStyle("-fx-font-size: 12;");
        propertyPrice.setStyle("-fx-font-size: 12; ");
        
        // Create constraints for each coloumn
        ColumnConstraints colWidth1 = new ColumnConstraints();
        colWidth1.setPercentWidth(10);
        ColumnConstraints colWidth2 = new ColumnConstraints();
        colWidth2.setPercentWidth(40);
        ColumnConstraints colWidth3 = new ColumnConstraints();
        colWidth3.setPercentWidth(50);
        
        // Sets the percentage constraints of each column in propertyResult
        propertyResult.getColumnConstraints().addAll(colWidth1, colWidth2, colWidth3);
        
        // Blinds an on hover background change event for each property
        changeBackgroundOnHoverUsingBinding(propertyResult);
        
        // Sets the width and height of each property
        propertyResult.setMaxWidth(450 * .80);
        propertyResult.setMinWidth(450 * .80);
        propertyResult.setMaxHeight(50);
        propertyResult.prefWidthProperty().bind(SEARCHRESULTBOX.widthProperty());
        
        // Sets the alignment for all of the property items
        propertyResult.setHalignment(propertyName, HPos.LEFT);
        propertyResult.setHalignment(propertyID, HPos.RIGHT);
        propertyResult.setHalignment(propertyAddresse, HPos.LEFT);
        propertyResult.setHalignment(propertyPrice, HPos.RIGHT);
        
        // Add the property information in the grid.
        propertyResult.add(propertyName, 0, 0);
        propertyResult.add(propertyID, 2, 0);
        propertyResult.add(propertyAddresse, 1, 1);
        propertyResult.add(propertyPrice, 2, 1);
        
        // Adds and event on mouse click event
        propertyResult.addEventHandler(MouseEvent.MOUSE_CLICKED, createNewPage(name, address, ID, price, information));
        
        SEARCHRESULTBOX.getChildren().add(propertyResult);
        
    }
    
    // Creates a new page when this function is fired
    public static Tab newSearchResult(String name, String address, String ID, String price, String information)
    {
        Tab propertyTab = new Tab();
        GridPane propertyResult    = new GridPane();
        GridPane propertyParagraph = new GridPane();
        Text propertyName = new Text(name);
        Text propertyAddresse = new Text(address);
        Text propertyID = new Text(ID);
        Text propertyPrice = new Text("$" + price);
        Text propertyInformation = new Text(information);
        ScrollPane scrollInformation  = new ScrollPane(propertyInformation);
        
        // Creates the constraints for the Grid panels rows and cols height and widths
        ColumnConstraints col0 = new ColumnConstraints();
        col0.setPercentWidth(2);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(49);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(80);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(10);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(100);
        RowConstraints rowSize =  new RowConstraints();
        rowSize.setMinHeight(20);
        
        // Formats each text property item
        propertyName.setStyle("-fx-font-size: 18;");
        propertyAddresse.setStyle("-fx-font-size: 12;");
        propertyID.setStyle("-fx-font-size: 16;");
        propertyPrice.setStyle("-fx-font-size: 16; ");
        
        // Sets the max and min width
        propertyResult.setMaxWidth(450);
        propertyResult.setMinWidth(450);
        
        // Sets the constraints on the rows and cols by percent size
        propertyResult.getColumnConstraints().addAll(col0,col1,col1);
        propertyResult.getRowConstraints().addAll(rowSize,rowSize,rowSize,rowSize,rowSize,rowSize);
        
        // Add the property information into the grid.
        propertyResult.add(propertyName, 1, 0);
        propertyResult.add(propertyID, 2, 0);
        propertyResult.add(propertyAddresse, 1, 2);
        propertyResult.add(propertyPrice, 2, 2);
        propertyResult.addRow(4,propertyParagraph);
        
        // Sets the alignment for all of the property items
        propertyResult.setHalignment(propertyName, HPos.LEFT);
        propertyResult.setHalignment(propertyID, HPos.RIGHT);
        propertyResult.setHalignment(propertyAddresse, HPos.LEFT);
        propertyResult.setHalignment(propertyPrice, HPos.RIGHT);
        
        // Sets the scroll bars policy for scrollInformation
        scrollInformation.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollInformation.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        
        // Sets the scroll areas max height as well as the min and max width. Also
        // sets the style for the scroll area.
        scrollInformation.setMaxHeight(400);
        scrollInformation.setMaxWidth(470);
        scrollInformation.setMinWidth(470);
        scrollInformation.setStyle("-fx-padding: 10px; -fx-background: #FFBC7A;");
        
        // Sets the width on the wrapping text
        propertyInformation.setWrappingWidth(430);
        propertyInformation.setStyle("-fx-font-size: 12;");
        
        // Sets the constraints on the cols by percent size
        propertyParagraph.getColumnConstraints().addAll(col4);
        
        // Sets the max and min width for propertyParagraph with the property information
        propertyParagraph.setMaxWidth(450);
        propertyParagraph.setMinWidth(450);
        
        // Adds the scroll panel to the propertyParagraph
        propertyParagraph.add(scrollInformation, 0, 0);
        
        // Adds the page to the propertyTab and allows the tab to be closed
        propertyTab.setContent(propertyResult);
        propertyTab.setClosable(true);
        
        // Adds the new page and tab to the main Tab panel
        TABPANE.getTabs().add(propertyTab);
        return propertyTab;
    }
    
    // Creates an on click event that creates a new page when a property is chosen
    public static EventHandler<Event> createNewPage(String name, String address, String ID, String price, String information)
    {
        EventHandler createPage = new EventHandler<Event>() 
        {
            @Override
            public void handle(Event event) 
            {
                newSearchResult(name, address, ID, price, information).setText(name);
            }
        };
        return createPage;
    }
    
    // Creates a binding to a node for an on hover event
    private static void changeBackgroundOnHoverUsingBinding(Node node) {
    node.styleProperty().bind(
      Bindings
        .when(node.hoverProperty())
          .then(
            new SimpleStringProperty(PROPERTYBUTTONBACKGROUNDHOVER + "-fx-border-color: black; -fx-border-radius: 5px; -fx-padding: 5px;")
          )
          .otherwise(
            new SimpleStringProperty(PROPERTYBUTTONBACKGROUNDREG + "-fx-border-color: black; -fx-border-size: 1px; -fx-border-radius: 5px; -fx-padding: 5px;")
          )
    );
  }
    
    // Launchs the main program
    public static void main(String[] args) {
        launch(args);
    }
    
}
