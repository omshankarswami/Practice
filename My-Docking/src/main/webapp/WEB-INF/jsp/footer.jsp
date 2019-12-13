
<script type="text/javascript">
	$(document).ready(function() {
		$('#datatable').dataTable({
			"scrollX" : true ,
			language: {
			    "sEmptyTable":      "Keine Daten in der Tabelle vorhanden",
			    "sInfo":            "_START_ bis _END_ von _TOTAL_ Eintr�gen",
			    "sInfoEmpty":       "Keine Daten vorhanden",
			    "sInfoFiltered":    "(gefiltert von _MAX_ Eintr�gen)",
			    "sInfoPostFix":     "",
			    "sInfoThousands":   ".",
			    "sLengthMenu":      "_MENU_ Eintr�ge anzeigen",
			    "sLoadingRecords":  "Wird geladen ..",
			    "sProcessing":      "Bitte warten ..",
			    "sSearch":          "Suchen",
			    "sZeroRecords":     "Keine Eintr�ge vorhanden",
			    "oPaginate": {
			        "sFirst":       "Erste",
			        "sPrevious":    "Zur�ck",
			        "sNext":        "N�chste",
			        "sLast":        "Letzte"
			    },
			    "oAria": {
			        "sSortAscending":  ": aktivieren, um Spalte aufsteigend zu sortieren",
			        "sSortDescending": ": aktivieren, um Spalte absteigend zu sortieren"
			    },
			    "select": {
			        "rows": {
			            "_": "%d Zeilen ausgew�hlt",
			            "0": "",
			            "1": "1 Zeile ausgew�hlt"
			        }
			    },
			    "buttons": {
			        "print":    "Drucken",
			        "colvis":   "Spalten",
			        "copy":     "Kopieren",
			        "copyTitle":    "In Zwischenablage kopieren",
			        "copyKeys": "Taste <i>ctrl</i> oder <i>\u2318</i> + <i>C</i> um Tabelle<br>in Zwischenspeicher zu kopieren.<br><br>Um abzubrechen die Nachricht anklicken oder Escape dr�cken.",
			        "copySuccess": {
			            "_": "%d Zeilen kopiert",
			            "1": "1 Zeile kopiert"
			        },
			        "pageLength": {
			            "-1": "Zeige alle Zeilen",
			            "_":  "Zeige %d Zeilen"
			        }
			    }
			}
		});

		$("[data-toggle=tooltip]").tooltip();

	});
</script>
</body>
</html>