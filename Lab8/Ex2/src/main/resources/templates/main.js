$(document).ready(function() {
    // Activate tooltip
    $('[data-toggle="tooltip"]').tooltip();

    // Select/Deselect checkboxes
    var checkbox = $('table tbody input[type="checkbox"]');

    // Select All checkboxes
    $("#selectAll").click(function() {
        checkbox.prop('checked', this.checked); // Deselect or select all checkboxes based on the 'select all' checkbox
    });

    // Deselect 'select all' checkbox if any individual checkbox is deselected
    checkbox.click(function() {
        if (checkbox.filter(':checked').length !== checkbox.length) {
            $("#selectAll").prop('checked', false); // Uncheck 'select all' if not all checkboxes are checked
        } else {
            $("#selectAll").prop('checked', true); // Check 'select all' if all checkboxes are checked
        }
    });
});
