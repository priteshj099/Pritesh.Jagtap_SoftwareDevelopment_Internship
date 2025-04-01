**# Load Booking Management API**

## Overview
The Load Booking Management API is a RESTful service designed to manage load and booking operations. It allows users to create, retrieve, update, and delete loads and bookings efficiently.

## Features
- Create, retrieve, update, and delete loads.
- Create, retrieve, update, and delete bookings.
- Handle load status updates (e.g., from POSTED to BOOKED or CANCELLED).
- Global exception handling for better error management.

## Technologies Used
- **Java**: The primary programming language.
- **Spring Boot**: Framework for building the RESTful API.
- **JPA (Java Persistence API)**: For database interactions.
- **PostgreSQL**: Database for storing load and booking information.
- **Maven**: Build automation tool.

## API Endpoints

### Load Endpoints
- **Create Load**
  - `POST /load`
  - Request Body: Load object
  - Response: Created Load object

- **Get Loads**
  - `GET /load`
  - Query Parameters: `shipperId`, `truckType` (optional)
  - Response: List of Load objects

- **Get Load by ID**
  - `GET /load/{loadId}`
  - Response: Load object

- **Update Load**
  - `PUT /load/{loadId}`
  - Request Body: Load object
  - Response: Updated Load object

- **Delete Load**
  - `DELETE /load/{loadId}`
  - Response: No Content

### Booking Endpoints
- **Create Booking**
  - `POST /booking`
  - Request Body: Booking object
  - Response: Created Booking object

- **Get Bookings**
  - `GET /booking`
  - Query Parameter: `transporterId` (optional)
  - Response: List of Booking objects

- **Get Booking by ID**
  - `GET /booking/{bookingId}`
  - Response: Booking object

- **Update Booking**
  - `PUT /booking/{bookingId}`
  - Request Body: Booking object
  - Response: Updated Booking object

- **Delete Booking**
  - `DELETE /booking/{bookingId}`
  - Response: No Content

## Error Handling
The API includes a global exception handler that returns appropriate HTTP status codes and error messages for various exceptions, such as:
- `400 Bad Request`: For invalid requests.
- `404 Not Found`: When a resource is not found.

## Things to Remember
1. **Load ID in Booking Table**: The `loadId` inside the booking table must correspond to the `id` in the load table. Ensure you use the correct `id` from the load table when entering schema data for the booking table. Otherwise, you will encounter an error stating "Load not found".

2. **Check Data Types in pgAdmin**: Verify the data types of columns in pgAdmin, particularly for timestamps, to ensure there is no mismatch of types. The ORM may mistakenly assign some byte format data type to certain columns. If such a case occurs, follow these steps in the SQL query pad:
   - a) Check the data types:
     ```sql
     SELECT column_name, data_type 
     FROM information_schema.columns 
     WHERE table_name = 'load';
     ```

   - b) Add temporary columns for correct data types:
     ```sql
     ALTER TABLE Load ADD COLUMN temp_loading_date TIMESTAMP;
     ALTER TABLE Load ADD COLUMN temp_unloading_date TIMESTAMP;
     ```

   - c) Update the temporary columns with converted data:
     ```sql
     UPDATE Load 
     SET temp_loading_date = convert_from(loading_date, 'UTF8')::TIMESTAMP,
         temp_unloading_date = convert_from(unloading_date, 'UTF8')::TIMESTAMP;
     ```

   - d) Rename the temporary columns back to the original names:
     ```sql
     ALTER TABLE Load RENAME COLUMN temp_loading_date TO loading_date;
     ALTER TABLE Load RENAME COLUMN temp_unloading_date TO unloading_date;
     ```

## Getting Started
1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd loadbooking
   ```

2. **Build the Project**
   ```bash
   mvn clean install
   ```

3. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the API**
   - The API will be available at `http://localhost:8080`.

## Dependencies
- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- PostgreSQL Driver
- Spring Boot Starter Validation
- Spring Boot Starter Test (for testing)

## License
This project is not licensed.

## Contributing
Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.

## Contact
For any inquiries, please contact [priteshj099@gmail.com].
