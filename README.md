## Language Selection

This project is available in multiple languages. To view the README in a different language, follow the instructions below:

- [English](README.md): Click the link to view the English version.
- [Polski](README.pl.md): Kliknij link, aby zobaczyć polską wersję.

# Warehouse Management API

Welcome to the Warehouse Management API repository! This API provides functionality for managing orders, automating restocking processes, and ensuring optimal stock levels in your warehouse. It also offers features to streamline the purchase order confirmation and delivery date calculation, along with a built-in messaging service for instant communication between clients and support.

## Features

### Order Management

The API allows businesses and clients to place orders on the website, track the status of their orders, and retrieve order details. Key features of the order management functionality include:

- Placing orders: Submitting new orders with customer details, product quantities, and delivery preferences.
- Order tracking: Monitoring the status of orders from placement to delivery.
- Order details: Retrieving detailed information about orders, including customer information, product details, and delivery dates.

### Automatic Restocking

The API automates the restocking process by generating purchase orders to contracted vendors for the required products. This ensures that the stock quantity always remains above the minimum level defined for each product. Key features of the automatic restocking functionality include:

- Stock monitoring: Constantly monitoring the stock levels of products in the warehouse.
- Reorder threshold: Determining the minimum stock level for each product that triggers the restocking process.
- Purchase order generation: Automatically creating purchase orders for products that fall below the reorder threshold.
- Vendor integration: Seamless integration with contracted vendors to streamline the restocking process.
- Purchase order tracking: Monitoring the status of purchase orders and providing updates on their progress.

### Purchase Order Confirmation and Delivery Date Calculation

The API provides purchase order confirmations to buyers along with calculated delivery dates. The estimated delivery date can be preset by the user or set individually for each purchase order confirmation. Please note that the API does not directly send emails but provides the necessary data and functionality up to the point of a possible API call service that handles email communication. Key aspects of this functionality include:

- Confirmation notifications: Generating purchase order confirmations containing relevant information.
- Delivery date calculation: Calculating the estimated delivery date based on various factors.
- Data availability: Providing confirmed purchase order details and calculated delivery dates for further processing by an email service or other communication mechanism.
- Integration flexibility: Allowing integration with an external email service or the implementation of a custom email sending mechanism.

### Messaging Service

The API includes a built-in messaging service that enables instant messaging between clients and support. This feature facilitates quick and efficient communication for addressing inquiries, resolving issues, and providing real-time assistance.

### JWT Authentication and Authorization

The Warehouse Management API incorporates JWT (JSON Web Token) authentication and authorization mechanisms to secure the endpoints and protect sensitive data. With JWT, you can ensure that only authorized users can access the API and perform specific actions. Key features of JWT authentication and authorization in the API include user authentication, token-based authorization, secure token transmission, token expiration and renewal, and fine-grained access control.

- Fine-grained access control: Allowing users to access their own data only, ensuring each user can retrieve and modify their specific resources such as messages or orders.
- Additional privileges for admin: Granting users with admin privileges access to specific administrative functionalities within the API.

## License

This project is licensed under the **All Rights Reserved** license. All rights to this software are exclusively reserved to the owner. Unauthorized use, modification, distribution, or reproduction of any part of this software is strictly prohibited without prior written permission from the owner.

Please note that the above description is a general overview and may require further refinement based on the specific implementation details of your Warehouse Management API.