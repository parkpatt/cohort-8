# Record Collection

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

## Data Layer

### Data Classes

* RecordRepository (interface)
* RecordFileRepository
* DataAccessException

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
* [ ] Create `findByArtist()` (optional)
* [ ] Create `update()`
* [ ] Create `deleteById()`

## Domain Layer

### Domain Classes

* RecordService
  * RecordRepository repository
* Result
  * List messages

* [ ] Create `RecordRepository` interface
* [ ] Create repository test double

## UI Layer
