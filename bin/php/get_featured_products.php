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

// get all products from products table
$result = mysql_query(" SELECT rank, id, name, description, price, discountPrice, retailer, image_url, url 
FROM 
(
(SELECT @r1 as rank, (@r1:= @r1 + 4) as nextrank, id, name, description, price, discountPrice, retailer, image_url, url 
	FROM product, (SELECT @r1:=1) r
	WHERE category = 1 and discountPrice > 0 and discountPrice < (price / 2) and discountPrice < 300 and discountPrice > 50
	ORDER BY insertOrder, id ASC)
UNION
(SELECT @r2 as rank, IF (@r2%2 = 0, @r2 := @r2+1, @r2 := @r2+7) as nextrank, id, name, description, price, discountPrice, retailer, image_url, url 
	FROM product, (SELECT @r2:=2) r
	WHERE category = 2 and discountPrice > 0 and discountPrice < (price / 2) and discountPrice < 300 and discountPrice > 50
	ORDER BY insertOrder, id ASC)
UNION 
(SELECT @r3 as rank, IF (@r3%8 = 0, @r3 := @r3 +6, @r3 := @r3+2) as nextrank, id, name, description, price, discountPrice, retailer, image_url, url 
	FROM product, (SELECT @r3:=6) r
	WHERE category = 3 and discountPrice > 0 and discountPrice < (price / 2) and discountPrice < 300 and discountPrice > 50
	ORDER BY insertOrder, id ASC) 
UNION
(SELECT @r4 as rank, IF (@r4%4 = 0, @r4 := @r4+3, @r4 := @r4+5) as nextrank, id, name, description, price, discountPrice, retailer, image_url, url 
	FROM product, (SELECT @r4:=4) r
	WHERE category = 4 and discountPrice > 0 and discountPrice < (price / 2) and discountPrice < 300 and discountPrice > 50
	ORDER BY insertOrder, id ASC)
) AS PRODUCTS
ORDER BY rank
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