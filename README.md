# Collection Box Manager

This application provides a backend REST API for managing collection boxes during fundraising events for a charity organization. It allows you to register collection boxes, assign them to events, add and transfer money, and generate financial reports.

---

## Features

### 1. Fundraising Event Management
- Create new fundraising events.
- Each event has its own account with a single currency.

### 2. Collection Box Management
- Register and remove collection boxes.
- Boxes can only be assigned to one event at a time.
- Boxes must be empty before assignment.
- Boxes can hold multiple currencies (e.g. USD, EUR, PLN).
- Boxes are emptied automatically upon removal.

### 3. Money Operations
- Add money to a box in supported currencies.
- Transfer money from a box to its assigned event's account.
- Money is converted to the event’s currency using online exchange rates.

### 4. Financial Reports
- View a financial summary of all fundraising events with their balances.

---

## Implementation Details

- **Backend**: Implemented in Java using Spring Boot framework.
- **Database**: Uses in-memory H2 relational database.
- **API**: Provides RESTful endpoints for all operations.
- **Security**: No authentication required (for simplicity).
- **Build Tool**: Maven.

---

## Instructions

### Building and Running the Application

1. **Clone the repository**:
   ```bash
   git clone https://github.com/MikitaBychkou/Collection_box_manager.git

2. **Navigate to the project directory**:

   ```bash
   cd collection-box-manager
   ```

3. **Build the project using Maven**:

   ```bash
   ./mvnw clean install
   ```

4. **Run the application**:

   ```bash
   java -jar target/collection-box-manager.jar
   ```

The application will start a local server exposing the REST API.

---

## Accessing REST Services

Use tools like Postman or cURL to interact with the API.

###  1. Create a fundraising event

**POST** `/fundraising-events`

```json
{
  "name": "Save the Earth",
  "currency": "EUR"
}
```

###  2. Financial report

**GET** `/fundraising-events/financial-report`

###  3. Register a new collection box

**POST** `/collection-boxes`

_No request body needed._

###  4. List all collection boxes

**GET** `/collection-boxes`

###  5. Assign box to fundraising event

**PUT** `/collection-boxes/{boxId}/assign`

```json
{
  "fundraisingEventId": 1
}
```

###  6. Add money to collection box

**PUT** `/collection-boxes/{boxId}/add-money`

```json
{
  "amount": 250,
  "currency": "USD"
}
```

###  7. Empty the collection box (transfer funds)

**PUT** `/collection-boxes/{boxId}/empty`

_No request body needed._

###  8. Remove a collection box

**DELETE** `/collection-boxes/{boxId}`

---

##  Currency-converter API

- Exchange rates are retrieved from an online service. If unavailable, default rates are used.




