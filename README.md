# monk-commerce-coupons-task
SDE II Task for Monk Commerce 2024
# 25-11-2024

**Project Overview**
This project is a Spring Boot microservice designed to manage and apply discount coupons for an e-commerce platform. It supports multiple coupon types, ensures easy extensibility for future needs, and provides a modular and clean codebase for RESTful API operations.

**Features Implemented**
1. Manage coupons via intuitive RESTful endpoints and CRUD Operations for Coupons in the below APIs implemented:
**API Endpoints**
POST:      /coupons  	     --> Create a new coupon
GET:	     /coupons	       --> Retrieve all coupons
GET:	     /coupons/{id}	 --> Retrieve a specific coupon by ID
PUT: 	     /coupons/{id}	 --> Update a specific coupon by ID
DELETE:   /coupons/{id}	   --> Delete a specific coupon by ID
POST: 	  /applicable-coupons -->	Fetch all applicable coupons for a cart
POST: 	  /apply-coupon/{id}	--> Apply a specific coupon to a cart

3. Discount Calculation (based on the discount_type specified in the coupon)
4. Multiple Coupon Types, the code is flexible to take any new type of Discount without disturbing the main business logic, you can just create
a new Java class for calculating the discount and the code will pick that logic when the coupon has that particular discount type mapped to it.
*Cart-wise: Discounts on the entire cart based on thresholds.
*Product-wise: Discounts for specific products.
5. Basic Unit Testing: Ensured the functionality of core features using JUnit and Mockito.
6. Error Handling: Manage common errors like invalid inputs, missing coupons, and unmet conditions.

 **Unimplemented Scenarios**
- Due to time constraints, the following scenarios remain unimplemented:

1. Advanced constraints like coupon expiry, location-based applicability, and user-specific coupons.
2. Edge cases involving conflicting coupon applications and multi-coupon optimizations.
3. end-to-end integration tests for higher coverage(Implemented only unit test cases)
4. BxGy: "Buy X, Get Y" deals with repetition limits (only cart-based and product-based discount is implemented)

**Assumptions**
1. Coupons are unique by ID.
2. Only one coupon is applied per cart or product at a time.
3. Discount types (cart-wise, product-wise, BxGy) are mutually exclusive.

 **Limitations**
 1. One major limitation is Scalability, Once we implement Cache like Redis we can improve response time, reduce latency and increase scalability.

**Scope for Future Enhancement**
1. In Coupons table, the default discount Type is percentage-based discount, we have scope for other discount types like cashbacks, flat discounts and the code is flexible and hence we can accomodate new type of discounts
   
2. The coupons table can have new columns like buy_quantity,get_quantity,min_cart_value,applicable_products:
   * Using buy_quantity we will be to track the required quantity of products to qualify for a "Buy X, Get Y" (BxGy) offer and offers like Buy 5, Get 1 Free; Buy 10, Get 3 Free etc.
   * we can use get_quantity to specify the quantity of free or discounted products provided under BxGy offers.
   * min_cart_value to specify the quantity of a particular product to be eligible to avail the discount
   * applicable_products: using the field we can specify the products which are eligible to the discount (the coupon cannot be used on all products)

3. New table **coupon_transactions** can be created which can be useful for:
   * Track how frequently coupons are used and Analyze user behavior to identify popular coupon types or discount patterns.
   * Investigate failed coupon applications (FAILED status) to identify invalid usage scenarios or system bugs.
   * Monitor individual users’ coupon usage (user_id) for targeted marketing campaigns or loyalty programs.

4. New Table **couponusers** can be created and used for:
   * Assign specific coupons to individual users based on their preferences or purchase history and Implement user-specific discounts or promotions.
   * Send promotional emails to users (email column) with exclusive coupon offers and Restrict certain coupons to specific users or user groups (e.g: VIP customers).
     
==================================================================== THE END ===============================================================================================  
