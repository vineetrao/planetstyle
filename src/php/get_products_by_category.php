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
//if (!empty($_GET($start))) $start_num = $_GET($start);
$start_num = (!empty($_GET['start'])) ? trim($_GET['start']) : "0" ;
$category = (!empty($_GET['category'])) ? trim($_GET['category']) : "0" ;


// get all products from products table
$result = mysql_query(" SELECT id, name, description, ROUND((price*@rate)) as price, ROUND((discountPrice*@rate)) as discountPrice, retailer, brand, image_url, url 
			FROM product, (select @rate:=rate from ExchangeRates where country_code='INR') AS ER
			WHERE category=$category and discountPrice>10
			ORDER BY insertOrder, id ASC
			LIMIT $start_num,5") 
		  or die(mysql_error());
 
// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["product"] = array();
 
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $product = array();
        $product["id"] = $row["id"];
        $product["name"] = $row["name"];
        $product["description"] = $row["description"];
        $product["price"] = $row["price"];
        $product["discountPrice"] = $row["discountPrice"];
        $product["retailer"] = $row["retailer"];
        $product["brand"] = $row["brand"];
        $product["image_url"] = $row["image_url"];
        $product["url"] = $row["url"];
        
 
        // push single product into final response array
        array_push($response["product"], $product);
    }
    // success
    $response["success"] = 1;
 
    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No products found";
 
    // echo no users JSON
    echo json_encode($response);
}
?>