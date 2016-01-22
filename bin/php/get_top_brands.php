<?php
 
/*
 * Following code will list all the products
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();

// get all products from products table
$result = mysql_query(" select brand, count(*) as count  from product where brand <> '' group by brand order by count desc limit 30") 
		  or die(mysql_error());
 
// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["brands"] = array();
 
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $brands = array();
        $brands["brand"] = $row["brand"];
        $brands["count"] = $row["count"];
        
        // push single product into final response array
        array_push($response["brands"], $brands);
    }
    // success
    $response["success"] = 1;
 
    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No brands found";
 
    // echo no users JSON
    echo json_encode($response);
}
?>