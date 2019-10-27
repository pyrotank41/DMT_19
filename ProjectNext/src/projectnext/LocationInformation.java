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
    
    private int[] placeNewItem(int newItem, int[] nums, String sisterSpace[], int currentIndex)
    {
        for(int i = 0; i < nums.length; i++)
        {
            if(currentIndex >= nums.length)
            {
                if(i == 0 && newItem > Integer.parseInt(sisterSpace[nums[i]]) )
                    nums[i] = currentIndex;
                else if(i != 0)
                {
                    if(Integer.parseInt(sisterSpace[nums[i - 1]]) > Integer.parseInt(sisterSpace[nums[i]]))
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
               {
                   nums[i] = currentIndex;
                   break;
               }
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
        else
            return null;
        
        if(numTopSisters > 5)
            numTopSisters = 5;
        
        int topSisters[] = new int[numTopSisters];
        
        for(int i = 0; i < sisters.length ; i++)
        {
            sisterName[i] = sisters[i].split(":")[0];
            sisterSpace[i] = sisters[i].split(":")[1];
            sisterFloor[i] = sisters[i].split(":")[2];
            topSisters = placeNewItem(Integer.parseInt(sisterSpace[i]), topSisters, sisterSpace, i);
        
        }
        for(int i = 0; i < topSisters.length ; i++)
        {
            System.out.println(topSisters[i] + " " + sisterSpace[topSisters[i]]);
        }
        int largestNum = 0;

        String sisterDetailsSentence = Name + "'s " + numTopSisters + " largest active tenants are ";
        
        for(int i = numTopSisters-1; i >= 0; i--)
        {
            sisterDetailsSentence += sisterName[topSisters[i]] + " with " + sisterSpace[topSisters[i]] + "SQ ft";
            
            if(i > 1)
                sisterDetailsSentence += ", ";
            else if(i == 1)
                sisterDetailsSentence += ", and ";
            else
                sisterDetailsSentence += ". ";
        }
        return sisterDetailsSentence;
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
        
        SalesPrice = decimalFormat.format(Double.valueOf(SalesPrice));
        Description += "The property most sold to " + Buyer + " for $" + SalesPrice + " by " + Seller + ". "; 
        
        Description += "The property is leased by " + LeasingCompany + " and managed by " + Manager + ". ";
        
        Description += GenerateSisterDetails();
        
        Description += "Asking rents are roughly $" + FloorPrice + "/SQft full service gross.";
        
        return Description;
    }
}
