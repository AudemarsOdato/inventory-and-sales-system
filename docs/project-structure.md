```
inventory-and-sales-system
├── docs
├── lib
|   ├── postgresql-42.2.29.jre7.jar - driver connector to postgresql database
|   └── dummy-data-products.sql
└── src
    ├── components
    ├── controllers - handlers and manipulates datas to be stored and or display to ui, seperates logic and data manipulation from the ui itself using functions and methods
    ├── database    - handles storing and updating products, sales records, sale items, users, and configuration
    ├── models      - custom data types or object models
    ├── pages       - pages or windows and their UI's
    └── utilities   - tools or code not related to core logic
```

controllers - this is where controller functions come in and shine, it means the logic is not written or happens here, you just call the controller function for this event
