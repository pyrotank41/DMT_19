package projectnext;

import javafx.application.Application;
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
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;


public class ProjectNext extends Application {
    private static final TabPane              TABPANE                        = new TabPane();
    private static final StackPane            ROOT                           = new StackPane();
    private static final VBox                 SEARCHRESULTBOX                = new VBox();     
    private static final ScrollPane           SEARCHRESULTBOXHOLDER          = new ScrollPane(SEARCHRESULTBOX);
    private static final String               PROPERTYBUTTONBACKGROUNDREG    = "-fx-background-color: #FFBC7A;";
    private static final String               PROPERTYBUTTONBACKGROUNDHOVER  = "-fx-background-color: #DD6F03;";
    private static final TextField            SEARCHBOX                      = new TextField();
    private static final int                  MAX_ID_LENGTH                  = 6;
    private static       LocationInformation  currentListOfProperties[];     
    private static final int                  WIDTH                          = 550;
    private static final int                  HEIGHT                         = 700;
    
    //This function gets called at the start of the program for Initialization 
    //of the default stage.
    @Override
    public void start(Stage primaryStage)
    {   
        SEARCHBOX.setOnKeyReleased(globalHotkeys());
        //Creates and add the default home tab
        TABPANE.getTabs().add(createHome());
        //Set the background of the tabs menu
        TABPANE.setStyle("-fx-background: #FFFFFF;");
        
        //Adds tab panel to ROOT StackPane
        ROOT.getChildren().add(TABPANE);
        //Set the background of the ROOT StackPane
        ROOT.setStyle("-fx-background: #FFA750;");
        
        Scene scene = new Scene(ROOT, WIDTH, HEIGHT);
        primaryStage.setTitle("NEXT");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
 
    private static String CleanedInput(StringBuilder userInput)
    {        
        for(int i = 0; i < userInput.length(); i++)
        {
            if(userInput.charAt(i) == ' ')
            {
                userInput.setCharAt(i, '_');
            }
        }
 
        userInput.insert(0, InputType(userInput.toString()));
    
        return userInput.toString();
    }
    
    
    private static String InputType(String userInput)
    {
        Boolean containsLetters = false;
        
        if(userInput.length() <= MAX_ID_LENGTH)
        {
            for(int i = 0; i < userInput.length(); i++)
            {
                if(Character.isLetter(userInput.charAt(i)))
                    containsLetters = true;
            }
            
            if(!containsLetters)
                return "pid/";
        }
        
        if(!ContainsRoadSuffix(userInput))
            return "pn/";
        
        return "pl/";
    }
    
    
    private static boolean ContainsRoadSuffix(String input)
    {
        String commonRoadSuffixes[] = { "_rd", "_road", "_boulevard", "_blvd", "_ave", "_avenue", "_st", "_street", "_way", "_dr", "_drive", "_pl", "_place", "_junction", "_jct" };
        
        for(int i = 0; i < commonRoadSuffixes.length; i++)
        {
            if(input.toLowerCase().contains(commonRoadSuffixes[i]))
            {
                return true;
            }
        }
        
        return false;
    }
    
    
    // Creates the default home screen
    public static Tab createHome()
    {
        final Tab        homeTab         = new Tab();       // Initialization of home tab   
        final GridPane   homeScreen      = new GridPane();  // Initialization of home screen Tab
        
        
        homeTab.setText("Home");
        homeTab.setStyle("-fx-font-size: 14;");
        homeTab.setClosable(false);
        
        // Sets the max height for the textbox as well as the width. Also make it editable
        SEARCHBOX.setMaxHeight(50);
        SEARCHBOX.prefWidthProperty().bind(homeScreen.widthProperty());
        SEARCHBOX.setEditable(true);
        
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
        homeScreen.addRow(0, SEARCHBOX);
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
        propertyResult.setMaxWidth(WIDTH * .80);
        propertyResult.setMinWidth(WIDTH * .80);
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
        SEARCHRESULTBOX.setAlignment(Pos.BASELINE_CENTER);
        
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
        propertyResult.setMaxWidth(WIDTH);
        propertyResult.setMinWidth(WIDTH);
        
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
        scrollInformation.setOnMouseClicked(sendVoice(information));
        // Sets the scroll areas max height as well as the min and max width. Also
        // sets the style for the scroll area.
        scrollInformation.setMaxHeight(400);
        scrollInformation.setMinHeight(400);
        scrollInformation.setMaxWidth(WIDTH + 20);
        scrollInformation.setMinWidth(WIDTH + 20);
        scrollInformation.setStyle("-fx-padding: 10px; -fx-background: #FFBC7A;");
        
        // Sets the width on the wrapping text
        propertyInformation.setWrappingWidth(WIDTH - 20);
        propertyInformation.setStyle("-fx-font-size: 12;");
        
        // Sets the constraints on the cols by percent size
        propertyParagraph.getColumnConstraints().addAll(col4);
        
        // Sets the max and min width for propertyParagraph with the property information
        propertyParagraph.setMaxWidth(WIDTH);
        propertyParagraph.setMinWidth(WIDTH);
        
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
    
    public static EventHandler<Event> sendVoice(String information)
    {
        EventHandler Voice = new EventHandler<Event>() 
        {
            @Override
            public void handle(Event event) 
            {
                try
                {
                    HTTPRequests httprequest = new HTTPRequests();
                    httprequest.SendRequest("voice/" + information.replaceAll(" ", "_"));
                }
                catch(IOException e)
                {

                }
            }
        };
        return Voice;
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
    
    
    private static EventHandler<KeyEvent> globalHotkeys()
    {
        return new EventHandler<KeyEvent>() {
            @Override
            
            public void handle(KeyEvent keyEvent)
            {

                switch(keyEvent.getCode())
                {
                    case ENTER:
                        try{
                            HTTPRequests httprequest = new HTTPRequests();
                            StringBuilder newInput = new StringBuilder(SEARCHBOX.getText());
                            String cleanedInput = CleanedInput(newInput);
                            String output = httprequest.SendRequest(cleanedInput);
                            createLocationInformation(output);
                            SEARCHBOX.clear();
                        }
                        catch(IOException e)
                        {
                
                        }
                    break;
                    
                    case ESCAPE:
                    break;
                }
            }
            
        };
    }
    
    
    public static void createLocationInformation(String content)
    {
        SEARCHRESULTBOX.getChildren().clear();
        String contentArray[];
        if(content != null)
            contentArray = content.split(",");
        else
            return;
        for(int i = 0; i < 24; i++)
        {
            if(contentArray[i].split(":").length > 1)
            {
                contentArray[i] = contentArray[i].split(":")[1];
            }
            contentArray[i] = contentArray[i].replaceAll("\"", "");
        }
        LocationInformation []temp = new LocationInformation[contentArray.length - 24];
        currentListOfProperties = temp;
        String tempStorage = "";
        for(int i = 24; i < contentArray.length; i++)
        {
            if(contentArray[i].split(":").length > 1 && contentArray[i].split(":").length < 3)
            {
                currentListOfProperties[i - 24]                    = new LocationInformation();
                currentListOfProperties[i - 24].PropertyID         = contentArray[0];
                currentListOfProperties[i - 24].Name               = contentArray[1];
                currentListOfProperties[i - 24].Address            = contentArray[2];
                currentListOfProperties[i - 24].City               = contentArray[3];
                currentListOfProperties[i - 24].State              = contentArray[4];
                currentListOfProperties[i - 24].Market             = contentArray[5];
                currentListOfProperties[i - 24].SubMarket          = contentArray[6];
                currentListOfProperties[i - 24].MicroMarket        = contentArray[7];
                currentListOfProperties[i - 24].Class              = contentArray[8];
                currentListOfProperties[i - 24].YearBuilt          = contentArray[9];
                currentListOfProperties[i - 24].BuildingSize       = contentArray[10];
                currentListOfProperties[i - 24].Stories            = contentArray[11];
                currentListOfProperties[i - 24].PropertyType       = contentArray[12];
                currentListOfProperties[i - 24].LeasingCompany     = contentArray[13];
                currentListOfProperties[i - 24].Manager            = contentArray[14];
                currentListOfProperties[i - 24].IsPrime            = contentArray[15];
                currentListOfProperties[i - 24].SubwayService      = contentArray[16];
                currentListOfProperties[i - 24].WalkScore          = contentArray[17];
                currentListOfProperties[i - 24].TransitScore       = contentArray[18];
                currentListOfProperties[i - 24].CrimeGrade         = contentArray[19];
                currentListOfProperties[i - 24].ExpansionPotential = contentArray[20];
                currentListOfProperties[i - 24].SalesPrice         = contentArray[21];
                currentListOfProperties[i - 24].Buyer              = contentArray[22];
                currentListOfProperties[i - 24].Seller             = contentArray[23];
                currentListOfProperties[i - 24].FirstYearRent      = contentArray[24];
                currentListOfProperties[i - 24].FloorPrice         = contentArray[i].split(":")[1].replaceAll("\"", "").replaceAll("#", "").replaceAll("}", "");
                currentListOfProperties[i - 24].FloorNum           = contentArray[i].split(":")[0].replaceAll("\"", "");
                currentListOfProperties[i - 24].SisterProperties   = tempStorage;
                propertyResult(contentArray[1] + " Floor: " + currentListOfProperties[i - 24].FloorNum, 
                        contentArray[2] + ", " + contentArray[3] + ", " + contentArray[4]
                        , "Property ID: " + contentArray[0], currentListOfProperties[i - 24].FloorPrice + "/sqft" , currentListOfProperties[i - 24].GetDetails());
                addSpacing();
                            }
            else
            {
                tempStorage += contentArray[i].split(":")[0].replaceAll("\"", "") + ":" 
                        + contentArray[i].split(":")[1].replaceAll("\"", "").replace('{', ' ').replaceAll(" ", "") 
                        + ":" + contentArray[i].split(":")[2].replaceAll("\"", "").replaceAll("}", "") + "\"";
            }
        }
    }
    
    
    // Launchs the main program
    public static void main(String[] args) 
    {
        launch(args);
    }
    
}
