document.addEventListener("DOMContentLoaded", () => {
    const sourceSelect = document.getElementById("source");
    const clickhouseConfig = document.getElementById("clickhouse-config");
    const flatfileConfig = document.getElementById("flatfile-config");
    const status = document.getElementById("status");
  
    // Toggle between ClickHouse and Flat File configurations
    sourceSelect.addEventListener("change", () => {
      if (sourceSelect.value === "clickhouse") {
        clickhouseConfig.style.display = "block";
        flatfileConfig.style.display = "none";
      } else {
        clickhouseConfig.style.display = "none";
        flatfileConfig.style.display = "block";
      }
    });
  
    // Fetch schema (dummy example)
    document.getElementById("fetch-schema").addEventListener("click", () => {
      // Simulate fetching schema
      status.innerText = "Fetching schema...";
      setTimeout(() => {
        // Dynamically load columns for selection (mock example)
        const columnsList = document.getElementById("columns-list");
        columnsList.innerHTML = `
          <label><input type="checkbox" value="col1"> Column 1</label>
          <label><input type="checkbox" value="col2"> Column 2</label>
          <label><input type="checkbox" value="col3"> Column 3</label>
        `;
        document.getElementById("columns-section").style.display = "block";
        status.innerText = "Schema fetched. Select columns.";
      }, 1000);
    });
  
    // Start ingestion (dummy example)
    document.getElementById("start-ingestion").addEventListener("click", () => {
      status.innerText = "Starting ingestion...";
      setTimeout(() => {
        status.innerText = "Ingestion completed. 500 rows transferred.";
      }, 2000);
    });
  });