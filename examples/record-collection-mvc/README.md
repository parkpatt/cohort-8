# Record Collection

## High Level Requirements

* Add a Record
* Update a Record.
* Remove a Record.
* Display all Records.
* Display all Records by Artist.

## Models

* Condition
  * `MINT`
  * `NEAR_MINT`
  * `VERY_GOOD_PLUS`
  * `VERY_GOOD`
  * `GOOD`
  * `FAIR`
  * `POOR`
* Record
  * int id
  * String artist
  * String title
  * Condition condition
  * double value

## Data Layer

### Data Classes

* RecordRepository (interface)
  * List findAll()
  * List findByArtist()
  * Record add()
  * boolean update()
  * boolean deleteById()
* RecordFileRepository implement RecordRepository
* DataAccessException extend Exception

### Data Steps

* [X] Create project `record-collection`
* [X] Add JUnit to pom.xml
* [X] Add package for `models`
* [X] Create `Record` class
* [X] Create `enum Condition`
  * `MINT`, `NEAR_MINT`, `VERY_GOOD_PLUS`, `VERY_GOOD`, `GOOD`, `FAIR`, `POOR`
* [X] Replace `condition` on `Record`
* [X] Show data file
* [X] Create `RecordFileRepository`
* [X] Create `findAll()`
  * BufferedReader, FileReader, catch IOException, swallow FileNotFound
* [X] Create `Record deserialize()`
  * split line, check for length, return `Record`
* [X] Create `DataAccessException`
* [X] Create `RecordFileRepositoryTest`
  * Create "known good state" & `@BeforeEach` setup
  * Test add `shouldFindAll()`
* [X] Create `add()`
* [X] Create `nextId()`
* [X] Create `writeToFile()`
  * PrintWriter
* [X] Create `String serialize()`
  * StringBuilder, DELIMITER, DELIMITER_REPLACEMENT
* [X] Create test for `shouldAdd()`
* [X] Create `findByArtist()` (optional)
* [X] Create `update()`
* [X] Create `deleteById()`

## Domain Layer

### Domain Rules

* Artist is required
* Title is required
* Condition is required
* Value should be between $0 - 10 million

### Domain Classes

* RecordService
  * RecordRepository repository
* RecordResult
  * List messages
  * Record record

### Domain Steps

* [X] Create `RecordRepository` interface
* [X] Create repository test double
* [X] Create `domain` package
* [X] Create `RecordService`
* [X] Inject repository
* [X] Pass through `findAll()`
* [X] Set up test class
  * How can we test the service in isolation?
  * Why is isolation important?
* [X] Create test double of repo
  * This gives us our "Known Good State" to test against
  * We have full control
  * Minimal implementation, just enough to support tests
* [X] Test findall() results
  * Verify size
  * Verify expected data
* [X] Create `add()` method
  * What is a good return type?
  * How should handle bad data?
  * Can we improve our return type?
* [X] Create `RecordResult`
* [X] Test `add()` happy path
  * Verify success
  * Verify expected payload
* [X] Test `add()` validation based on business rules defined
  * Verify not success
  * Verify expected messages
  * We can't test every bad value, how should we prioritize?
    * Min/max values? Length?
* [ ] Create `update()` method
  * Do the same business rules apply?
  * Are there rules that apply here but not to `create()`?
* [ ] Extract `validate()` method
  * Isolate shared validation
* [ ] Revisit `create()` tests, see if we broke anything
* [ ] Test `update()`

## UI Layer

### UI Classes

* View
  * Scanner scanner
* Controller
  * RecordService service
  * View view

### UI Steps

* We finally get to run our app!
* [ ] Create `View`
* [ ] Create `Controller`
  * field for `view`
  * field for `service`
  * generate constructor
  * add `run` method & print to confirm
* [ ] Create `App -> main`
* [ ] Wire up dependencies
  * Try instantiate `Controller` (need view)
  * Instantiate `View` and pass in
  * Try instantiate `Service`, (need repo)
  * Instantiate `RecordFileRepository` with production filePath, complete DI
* [ ] Run app!
* [ ] Create menu loop in `runMenu()`
* [ ] Create `getMainMenuOption()` in `view`
  * "1. Create Record"
  * "2. View Record"
  * ....
  * "0. Exit"
* [ ] Create menu loop in `runMenu()`
  * Prompt for `option`
  * `switch(option)` ...
  * default "I don't understand."
* [ ] Create `run()` app launch
  * launch `runMenu()`
  * Catch DataAccessException
* [ ] Stub out methods for menu options
* [ ] Extract `printHeader()` method in `View`?
* [ ] Create `displayRecords()` in `View` & print list
  * This seems like a good time to make a friendly name for Condition, etc.?
  * Revisit enum, add `displayName`
* [ ] Add `makeRecord()` in `View`
  * Import some premade input reader helpers
  * Prompt for data
  * Maybe a menu for `type` using our new friendly `displayName`?
  * Handle success
  * Handle error (maybe `displayMessage()` method?)
* [ ] Add view for a single `Record`?
  * Reuse list view for menu?
  * Another use for our friendly type name!
* [ ] View by `type`?
* [ ] Update?
* [ ] Delete?
* [ ] (Stretch goal) Refactor `readCondition` to menu
* [ ] (Stretch goal) Refactor main menu to enum?
