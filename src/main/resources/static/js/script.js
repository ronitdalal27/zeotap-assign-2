function toggleSourceFields() {
  let source = document.getElementById("sourceSelection").value;
  document.getElementById("clickhouseFields").style.display = (source === "clickhouse") ? "block" : "none";
  document.getElementById("flatFileFields").style.display = (source === "flatfile") ? "block" : "none";
  document.getElementById("columnSelection").style.display = "none";
  document.getElementById("status").innerHTML = "";
  document.getElementById("result").innerHTML = "";
}

function fetchTables() {
  document.getElementById("status").innerHTML = "Fetching tables...";
  setTimeout(() => {
      document.getElementById("tables").innerHTML = "<label><input type='radio' name='table' value='test_table'> test_table</label>";
      document.getElementById("status").innerHTML = "Tables fetched successfully!";
      document.getElementById("columnSelection").style.display = "block";
      loadColumns(["id", "name", "age"]); // Simulating fetched columns
  }, 2000);
}

function loadFlatFileColumns() {
  document.getElementById("status").innerHTML = "Parsing Flat File...";
  setTimeout(() => {
      document.getElementById("status").innerHTML = "Columns loaded!";
      document.getElementById("columnSelection").style.display = "block";
      loadColumns(["id", "name", "age"]); // Simulated column extraction
  }, 2000);
}

function loadColumns(columnList) {
  let columnsDiv = document.getElementById("columns");
  columnsDiv.innerHTML = "";
  columnList.forEach(col => {
      columnsDiv.innerHTML += `<label><input type="checkbox" value="${col}"> ${col}</label>`;
  });
}

function previewData() {
  document.getElementById("status").innerHTML = "Previewing data...";
  setTimeout(() => {
      document.getElementById("result").innerHTML = "Displaying first 100 records...";
      document.getElementById("status").innerHTML = "Preview successful!";
  }, 2000);
}

function startIngestion() {
  document.getElementById("status").innerHTML = "Ingestion process started...";
  setTimeout(() => {
      let selectedColumns = Array.from(document.querySelectorAll("#columns input:checked")).map(el => el.value);
      document.getElementById("result").innerHTML = "Data ingested successfully! Selected Columns: " + selectedColumns.join(", ");
      document.getElementById("status").innerHTML = "Ingestion completed!";
  }, 3000);
}