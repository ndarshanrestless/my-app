package com.mycompany.app;
import com.google.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;

@Builder
@AllArgsConstructor
public class Triangle {
    public static class Builder {
    }

    int area;
    HashMap sides;

    public static void main(String[] args) {

        ArrayList<String> li = new ArrayList<String>(10);

        updatedList(li);
        System.out.println(li);

        Triangle tri = Triangle.builder().area(10).build();
        if (tri.sides == null){
            tri.area = 25;
        }


        Triangle.updatedField(tri);

        System.out.println(tri.area);
        return;
    }

    public static void updatedList(ArrayList li)
    {
        li.add("Darshan");
        li.add("nidhi");
    }

    public static void updatedField(Triangle triIn)
    {
        // dbSysDOToUpdateWithTags.toBuilder().tags(newTagsFromAuthz).build();
        //triIn.
    }
    public static String[] isValidTriangle(String [] abc)
    {
        String[] retStr = new String[abc.length];
        //Isosceles, "None of these", "Equivateral"

        for(int i=0; i <= abc.length; i++)
        {
            String[] sides = abc[i].split(" ");

            if(sides[0]==sides[1] && sides[0]==sides[2] && sides[2]==sides[0])
            {
                retStr[i] = "Equivateral";

            }
            else if (sides[0]==sides[1] || sides[0]==sides[2] || sides[2]==sides[0])
            {
                retStr[i] = "Isosceles";
            }
            else
            {
                retStr[i] = "None of these";
            }
        }

        return retStr;
    }

}
