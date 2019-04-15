package com.mycompany.app;


/**
 * Find the path in the matrix which has 1's
 *
 */
import java.io.*;

import com.oracle.bmc.ConfigFileReader;
import com.oracle.bmc.Region;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.core.VirtualNetworkClient;
import com.oracle.bmc.core.model.CreateVcnDetails;
import com.oracle.bmc.core.model.UpdateVcnDetails;
import com.oracle.bmc.core.model.Vcn;
import com.oracle.bmc.core.requests.CreateVcnRequest;
import com.oracle.bmc.core.requests.GetVcnRequest;
import com.oracle.bmc.core.requests.UpdateVcnRequest;
import com.oracle.bmc.core.responses.CreateVcnResponse;
import com.oracle.bmc.core.responses.GetVcnResponse;
import com.oracle.bmc.core.responses.UpdateVcnResponse;
import com.oracle.bmc.identity.IdentityClient;
import com.oracle.bmc.identity.model.CreateTagDetails;
import com.oracle.bmc.identity.model.CreateTagNamespaceDetails;
import com.oracle.bmc.identity.model.Tag;
import com.oracle.bmc.identity.model.TagNamespace;
import com.oracle.bmc.identity.model.TagNamespaceSummary;
import com.oracle.bmc.identity.model.TagSummary;
import com.oracle.bmc.identity.model.UpdateTagDetails;
import com.oracle.bmc.identity.model.UpdateTagNamespaceDetails;
import com.oracle.bmc.identity.requests.CreateTagNamespaceRequest;
import com.oracle.bmc.identity.requests.CreateTagRequest;
import com.oracle.bmc.identity.requests.GetTagNamespaceRequest;
import com.oracle.bmc.identity.requests.GetTagRequest;
import com.oracle.bmc.identity.requests.ListTagNamespacesRequest;
import com.oracle.bmc.identity.requests.ListTagsRequest;
import com.oracle.bmc.identity.requests.UpdateTagNamespaceRequest;
import com.oracle.bmc.identity.requests.UpdateTagRequest;
import com.oracle.bmc.identity.responses.CreateTagNamespaceResponse;
import com.oracle.bmc.identity.responses.CreateTagResponse;
import com.oracle.bmc.identity.responses.UpdateTagNamespaceResponse;
import com.oracle.bmc.identity.responses.UpdateTagResponse;
import com.oracle.bmc.model.BmcException;

import com.oracle.bmc.Region;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.objectstorage.ObjectStorage;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import com.oracle.bmc.objectstorage.model.CreateBucketDetails;
import com.oracle.bmc.objectstorage.requests.CreateBucketRequest;
import com.oracle.bmc.objectstorage.requests.GetBucketRequest;
import com.oracle.bmc.objectstorage.requests.GetNamespaceRequest;
import com.oracle.bmc.objectstorage.requests.PutObjectRequest;
import com.oracle.bmc.objectstorage.responses.GetBucketResponse;
import com.oracle.bmc.objectstorage.responses.GetNamespaceResponse;

//import com.oracle.pic.sgw.api.clients.OverlayInternalApiTestClientSetup;
//import com.oracle.pic.sgw.api.OverlayInternalServiceGatewayClient;
//import com.oracle.pic.sgw.model.SgwVcnHeaderMapping;
//import com.oracle.pic.commons.client.authentication.ClientAuthenticationConfig;

//import com.oracle.pic.commons.client.authentication.FileBasedAuthenticationConfig;


import java.util.ArrayList;
import java.util.List;

public class App {


    //static OverlayInternalServiceGatewayClient internalOverlayApiTestClient;

    public static void main(String[] args) throws IOException {
        //static OverlayInternalServiceGatewayClient internalOverlayApiTestClient;
        //internalOverlayApiTestClient.listSgwVcnHeaderMappings(1, null);

        //readFirstLineFromFile("/tmp/blah");
        //doOciSgwTest();
    }

    static void readFirstLineFromFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            System.out.println(br.readLine());
        } catch (Exception ex) {

        }
    }


    static void doOciSgwTest() {
        String configurationFilePath = "~/.oci/darshan_config";
        String profile = "DEFAULT";
        AuthenticationDetailsProvider provider = null;
        try {
             provider = new ConfigFileAuthenticationDetailsProvider(configurationFilePath, profile);

        } catch (Exception ex) {
            System.out.println("Ran into error" + ex.getMessage());
            return;
        }


        ObjectStorage client = new ObjectStorageClient(provider);
        //client.setRegion(Region.US_PHOENIX_1);


        final String bucket = "xyz";

        System.out.println("Getting the namespace.");
        GetNamespaceResponse namespaceResponse =
                client.getNamespace(GetNamespaceRequest.builder().build());
        String namespaceName = namespaceResponse.getValue();

        System.out.println("Creating Get bucket request");
        List<GetBucketRequest.Fields> fieldsList = new ArrayList<>(2);
        fieldsList.add(GetBucketRequest.Fields.ApproximateCount);
        fieldsList.add(GetBucketRequest.Fields.ApproximateSize);
        GetBucketRequest request =
                GetBucketRequest.builder()
                        .namespaceName(namespaceName)
                        .bucketName(bucket)
                        .fields(fieldsList)
                        .build();

        System.out.println("Fetching bucket details");
        GetBucketResponse response = client.getBucket(request);

    }
}
/*
    testing enum
    public static void main(String[] args){

        System.out.println(TagRuleResourceKind.dbsystem.getText());
        System.out.println(TagRuleResourceKind.database.getText());

    }

    public enum TagRuleResourceKind
    {
        dbsystem("db-systems"),
        database("databases");

        @Getter
        private String text;

        TagRuleResourceKind(String text) {
            this.text = text;
        }

    }

    // testing exception
    public static void main(String[] args) throws IllegalArgumentException {
        {
            errorFunc();
            //fileFunc();

    }

    private static  void fileFunc() throws IOException{
        FileReader file = new FileReader("C:\\test\\a.txt");
        BufferedReader fileInput = new BufferedReader(file);

        // Print first 3 lines of file "C:\test\a.txt"
        for (int counter = 0; counter < 3; counter++)
            System.out.println(fileInput.readLine());

        fileInput.close();

    }

    private static void errorFunc(){
        System.out.println("inside errorFunc");

        throw new IllegalArgumentException("errorFunc");
    }


    // special getter for optional field
    public static Optional<String> getPostcode(String in) {
        Option<String> postcode = new String ("");
        return Optional.ofNullable(postcode);
    }

    private static ArrayList<List<Integer>> find_path(int[][] input, int m, int n, final int rows, final int cols,
                              ArrayList<List<Integer>> retArr){

        if(inputFlag[m][n] == true){
            return retArr;
        }
        // if you reached the last elem then push this
        if (m == rows-1 && n == cols-1){
            if (input[m][n] == 1) {
                List<Integer> inArr = new ArrayList<Integer>(2);
                inArr.add(m);
                inArr.add(n);
                retArr.add(inArr);
                return retArr;
            }
            else{ // input[m][n] == 0
                // dont add anything
                return retArr;
            }
        }

        if(input[m][n] == 0)
        {
            return retArr;
        }
        else{
            // add the current node
            List<Integer> inArr = new ArrayList<Integer>(2);
            inArr.add(m);
            inArr.add(n);
            retArr.add(inArr);
            inputFlag[m][n] = true;

            if(inbounds(m,n-1, rows, cols))
            {
                ArrayList<List<Integer>> leftRet = find_path(input, m, n-1, rows, cols, retArr);
                if(hasDest(leftRet, rows, cols)){
                    return leftRet;
                }
            }
            if(inbounds(m,n+1, rows, cols))
            {
                ArrayList<List<Integer>> rightRet = find_path(input, m, n+1, rows, cols, retArr);
                if(hasDest(rightRet, rows, cols)){
                    return rightRet;
                }
            }
            if(inbounds(m-1,n, rows, cols))
            {
                ArrayList<List<Integer>> topRet = find_path(input,m-1, n, rows, cols, retArr);
                if(hasDest(topRet, rows, cols)){
                    return topRet;
                }
            }
            if(inbounds(m+1,n, rows, cols))
            {
                ArrayList<List<Integer>> bottomRet = find_path(input,m+1, n, rows, cols, retArr);
                if(hasDest(bottomRet, rows, cols)){
                    return bottomRet;
                }
            }

            retArr.remove(retArr.size()-1);
            return retArr;
        }

    }

    private static boolean inbounds(int m, int n, int rows, int cols){
        try {
            if (m <= rows - 1 && m >= 0 && n <= cols - 1 && n >= 0) {
                return true;
            } else return false;
        }
        catch (NullPointerException e){
            System.out.println("abc");
        }
        return false;
    }

    private static boolean hasDest(ArrayList<List<Integer>> testArrList, int rows, int cols){
        if (testArrList.size() == 0){
            return false;
        }

        List<Integer> lastNode = new ArrayList<Integer>();
        lastNode.add(rows-1);
        lastNode.add(cols-1);

        return testArrList.contains(lastNode) ? true : false;
    }


    Given an unsorted integer array, find the first missing positive integer.

    For example,
    Given [1,2,0] return 3,
    and [3,4,-1,1] return 2.

    Your algorithm should run in O(n) time and uses constant space.

    public static int firstMissingPositive(int[] nums) {
        int inLen = nums.length;
        int largest = 0;

        for(int each: nums) {
            if (each <= 0)
                continue;
            if(largest < each)
                largest = each;

        }
        boolean[] ref = new boolean[largest+1];


        ref[0] = true; // zero is neither +ve nor -ve
        int index = 0;
        for(int each: nums) {
            if (each <= 0)
                continue;
            ref[each] = true;
        }

        for(boolean each: ref) {
            if(index == 0) {
                index++;
                continue;
            }
            index++;
            if (each == false)
                return index-1;
        }
        return largest+1;
    }

}


@JsonDeserialize(builder=BuildClass.Builder.class)
class BuildClass {
    public static class Builder {

    }

    int attr1;

    public static void main(String[] args) {
        BuilderExample bObj1 = BuilderExample.builder().age(25).occupation("blha").build();
        //bObj.occupations.add("raa");
        BuilderExample bObj2 = BuilderExample.builder().age(26).occupation("aaa").build();
        //BuilderExample obj2 = bObj.builder().name("piyanka").build();

       // bObj.occupations.add("hello");
       // bObj.occupations.add("world");
        System.out.println(bObj1.age);
    }
}
*/