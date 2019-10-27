import java.text.DecimalFormat;

public class LocationInformation 
{
    public String   PropertyID,
                    Name,
                    Address,
                    City,
                    State,
                    Market,
                    SubMarket,
                    MicroMarket,
                    Class,
                    YearBuilt,
                    BuildingSize,
                    Stories,
                    PropertyType,
                    LeasingCompany,
                    Manager,
                    IsPrime,
                    SubwayService,
                    WalkScore,
                    TransitScore,
                    CrimeGrade,
                    ExpansionPotential,
                    SalesPrice,
                    Buyer,
                    Seller,
                    FirstYearRent,
                    FloorNum,
                    FloorPrice;
    public LocationInformation()
    {
        
    }
    public String GetDetails()
    {
        String Description = "";
        
        Description += String.format
        (
                "%s is located at %s, %s %s", 
                Name, Address, City, State
        );
        
        if(Market != null)
        {
            Description += " in the ";
            if(SubMarket != null)
            {
                Description += SubMarket + " Submarket of the "; 
            }
            
                Description += Market + "Market. ";
        }
        
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        BuildingSize = decimalFormat.format(Double.valueOf(BuildingSize));
        
        Description += String.format
                (
                "This property is a Class %s %s building, built in %s, standing %s stories tall with a total size of %s SQ ft. ",
                Class, PropertyType, YearBuilt, Stories, BuildingSize
                );
        
        return Description;
    }
}
