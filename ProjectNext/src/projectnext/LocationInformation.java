package projectnext;

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
                    FloorPrice,
                    SisterProperties;
    
    private int[] placeNewItem(int newItem, int[] nums, int currentIndex)
    {
        for(int i = 0; i < nums.length; i++)
        {
            if(currentIndex >= nums.length)
            {
                if(i == 0 && newItem > nums[i])
                    nums[i] = newItem;
                else if(i != 0)
                {
                    if(nums[i - 1] > nums[i])
                    {
                        int temp = nums[i];
                        nums[i] = nums[i - 1];
                        nums[i - 1] = temp;
                    }
                }
            }
            else
            {
               if(nums[i] == 0)
                   nums[i] = newItem;
            }
        }
        return nums;
    }
    // name: sqft: floors
    private String GenerateSisterDetails()
    {
        String sisters[] = SisterProperties.split("\"");
        String sisterName[] = new String[sisters.length];
        String sisterSpace[] = new String[sisters.length];
        String sisterFloor[] = new String[sisters.length];
        
        int numTopSisters = 0;
        
        if((sisters.length > 2))
            numTopSisters = sisters.length / 2;
        
        if(numTopSisters > 5)
            numTopSisters = 5;
        
        int topSisters[] = new int[numTopSisters];
        
        for(int i = 0; i < sisters.length ; i++)
        {
            sisterName[i] = sisters[i].split(":")[0];
            sisterSpace[i] = sisters[i].split(":")[1];
            sisterFloor[i] = sisters[i].split(":")[2];
            topSisters = placeNewItem(Integer.parseInt(sisterSpace[i]), topSisters, i);
        }
        int largestNum = 0;

        return null;
    }

    public String GetDetails()
    {
        GenerateSisterDetails();
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
        
        SalesPrice = decimalFormat.format(Double.valueOf(SalesPrice));
        Description += "The property most sold to " + Buyer + " for $" + SalesPrice + " by " + Seller + ". "; 
        
        Description += "The property is leased by " + LeasingCompany + " and managed by " + Manager + ". ";
        
        Description += "Asking rents are roughly $" + FloorPrice + "/SQft full service gross.";
        
        return Description;
    }
}
