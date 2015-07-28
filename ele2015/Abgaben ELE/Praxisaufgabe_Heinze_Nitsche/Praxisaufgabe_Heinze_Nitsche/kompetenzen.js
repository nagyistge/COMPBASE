
//~ $( document ).ready(function() = $(function() Anfang des Programms
$(function() {

    //Das Wartebilds und die Box mit Scrollbalken verbergen
    $("#bild").hide();
    $("#umrahmung").hide();
    var user = null;

    //Wenn auf Button "einloggen" geklickt wurde, wird der Benutzername in "name" gespeichert
    $("#einloggen").on("click", function () {
        var benutzerName = $("#username").val();

        //Falls kein Benutzername eingegeben wurde, soll die Fehlermeldung erscheinen
        if ( benutzerName.replace(/\s/g, "") == "" ) {
            alert("Ungültiger/leerer Benutzername!");
            return;
        }

        user = benutzerName;

        //Anzeigen der Seite mit allen Kompetenzen
        $("#liste-aller-kompetenzen").show();
        //Ausblenden der Seite mit Anmeldeformular
        $("#anmeldeformular").hide();
        //Angemeldet als "Benutzername" anzeigen
        $("#angemeldet-als").text(user);
    });

    //Wenn auf "Gelernte Kompetenzen anzeigen" geklickt wird, wird die Funktion ladeGelernteKompetenzen() aufgerufen
    //und die gelernten Kompetenzen werden für angemeldeten Benutzer gezeigt
    $("#gelernte-kompetenzen-anzeigen").on("click", function (e) {
        //Unterdrücken der Standardaktion des Ereignisses
        e.preventDefault();
        $("#bild2").show();
        //Zeigen der Ansicht "Gelernte Kompetenzen"
        $("#liste-gelernter-kompetenzen").show();
        //Ausblenden der Ansicht "Kompetenzen"
        $("#liste-aller-kompetenzen").hide();

        //III. GET: Lade Fortschritt eines Nutzers bezüglich einer Menge von Kompetenzen
        ladeGelernteKompetenzen(user).then(function (data) {
            $("#bild2").hide();
            //Man braucht hier nur die Kompetenzen-Links, also nutzen wir die Variable "data" als Shortcut
            data = data.mapUserCompetenceLinks;

            //Hier sollen die geladenen Kompetenzen angezeigt werden
            var $kompetenzen = $("#gelernte-kompetenzen");
            //Klasse "comments" braucht man damit die Kommentaren unterstrichen sind, wird bei index.html benutzt
            var $eintrag = $("<li><span class=\"competence\"></span><br /><span class=\"comments\">" +
                "Kommentare:" + "</span><ul></li></li>");
            //Klasse "comment" braucht man damit bei Kommentaren keine Aufzählungspunkte davor stehen, wird bei index.html benutzt
            //Erstellung eines Templates für die Inhalte von Kommentaren
            var $kommentar = $("<li class=\"comment\"></li>");
            var $kopie, $kopie2, comments;

            //Ersetzen der Kompetenzen durch leeren String
            $kompetenzen.html("");

            //Einfügen aller gefundenen Kompetenzen in den DOM
            for ( var name in data ) {
                //Erstellung einer nutzbare Kopie für die Kommentarliste
                $kopie = $eintrag.clone();
                //Einfügen des Namens (Textes) der Kompetenz an die entsprechende Stelle
                $kopie.find("span.competence").text(name);

                //Anfragen, ob für eine Kompetenz Kommentar eingetragen sind
                comments = data[name][0].comments;

                //Wenn es Kommentare gibt, werden diese angezeigt
                if ( comments.length > 0 ) {
                    //Anzeigen aller Kommentare
                    for ( var i in comments ) {
                        //Erstellung einer nutzbare Kopie für einen Kommentar
                        $kopie2 = $kommentar.clone();
                        //Einfügen des Kommentartextes
                        $kopie2.text(comments[i].commentName);
                        //Hinzufügen des Kommentars in die Liste
                        $kopie.find("ul").append($kopie2);
                    }
                 //Wenn es von dem Nutzer keine Kommentare gibt, werden die entsprechenden Elemente gelöscht
                } else {
                    $kopie.find("span.comments").remove();
                    $kopie.find("ul").remove();
                }

                //Kompetenzliste dem DOM hinzufügen
                $kompetenzen.append($kopie);
            }
        });
    });

    //Man befindet sich bei gelernten Kompetenzen und möchte zurück zu allen Kompetenzen
    $("#alle-kompetenzen-anzeigen").on("click", function (e) {
        e.preventDefault();
        //Alle Kompetenzen sind sichtbar
        $("#liste-aller-kompetenzen").show();
        //Gelernte Kompetenzen sind verborgen
        $("#liste-gelernter-kompetenzen").hide();
    });

    //Auf "Kompetenzen laden" klickt man nur ein mal nach jeder Anmeldung
    $("#kompetenzen-laden").on("click", function() {
        $("#bild").show();
        $("#umrahmung").show();
        //Während die Kompetenzen geladen werden erscheint der Text "Kompetenzen werden geladen ..."
        $(this).text("Kompetenzen werden geladen ...");
        //Der Button ist deaktiviert
        $(this).attr("disabled", "disabled");

        //Suche alle Elemente mit id "kompetenzen"
        var $kompetenzen    = $("#kompetenzen");
        var $kategoriePunkt = $("<li><span></span><ul></ul></li>");
        var $kompetenzPunkt = $("<li></li>");

        //I. GET: Lade alle Kompetenzen für einen Kurs (Checkbox und Kommentar sind da)
        ladeKompetenzen().then(function (data) {
            $("children", data)
                //Die obersten Kompetenzen (== "Kategorien"), die Kinder haben, werden gefiltert und die vier ersten
                .filter(function (index) {
                    return ( $(this).children("children").length > 0 || index < 4);
                })
                //Speichere den Namen der Kategorie  zwischen <span></span>
                .each(function() {
                    var $liste = $kategoriePunkt.clone();
                    $liste.find("span").text($(this).attr("name"));
                    //Schau bei jeder Kategorie, die Kinder hat rein
                    if ( $(this).children("children").length > 0 ) {
                        //Hole bei jedem Kind seinen Namen raus...
                        $(this).children("children").each(function () {
                            var $kompetenzListe = $kompetenzPunkt.clone();
                            $kompetenzListe.html(
                                '<input type="checkbox" /><span>' +
                                $(this).attr("name") +
                                '</span><br />' +
                                '<input type="text" size="100" maxlength="100"/>' +
                                '<button type="button">Kommentar speichern</button>');
                            //...finde in der Liste <ul></ul> und füge da alle Namen ein
                            $liste.find("ul").append($kompetenzListe);
                        });
                    }
                    //Die Liste enthält Kategorien mit Kompetenzen
                    $kompetenzen.append($liste);
                });
        })
        .then(function() {
        //Nach dem die Kompetenzen geladen wurden, verschwindet der Button "Kompetenzen laden"
            $("#kompetenzen-laden").hide();
            $("#bild").hide();
        });
    });

    var $kompetenzen = $("#kompetenzen");
    $kompetenzen.click(function (e) {
        //Ignorierung aller Elemente, die nicht als abgehakt markiert sind, wenn angehackt dann...
        if($(e.target).is(":checked")) {
            //Ermittlung der Kompetenz
            var $competence = $(e.target).siblings("span");
            //Markierung der Kompetenz als gelernt
            //IV.Ein Nutzer hat eine Kompetenz gelernt
            hatKompetenzGelernt("university", "maryna", "teacher", user, $competence.text(), "app,link");
        }
    });

    $kompetenzen.on("click", function(e) {
        if ( $(e.target).is("button") ) {
            //Ermittlung des Kommentartextes
            var $kommentar = $(e.target).siblings("input[type=text]");
            //Ermittlung der zugehörigen Kompetenz
            var $kompetenz = $(e.target).siblings("span");

            //Kommentar speichern
            //V. POST: Kommentar zu dem Lernen einer Kompetenz
            speicherKommentar($kompetenz.text(), user, "university", "student", $kommentar.val(), function (data) {
            });
            alert( "Kommentar wurde erfolgreich gespeichert!" );
        }
    });
});


//I. Lade alle Kompetenzen für einen Kurs
function ladeKompetenzen() {
    return Promise.resolve($.get("http://localhost:8084/competences/xml/competencetree/university/all/nocache"));
}
//III. GET: Lade Fortschritt eines Nutzers bezüglich einer Menge von Kompetenzen
function ladeGelernteKompetenzen(user, callback) {
    return Promise.resolve($.get("http://localhost:8084/competences/json/link/overview/" + user, callback));
}

//Der Name der Kompetenzen ($kompetenz.text()-->linkdId) beinhaltet Sonderzeichen, die durch UTF-8-Format-Zeichen ersetzt werden
function konvertiere(data) {
    var replace = {
        "Ã¤": "ä",
        "Ã„": "Ä",
        "Ã¼": "ü",
        "Ãœ": "Ü",
        "Ã¶": "ö",
        "Ã–": "Ö",
        "ÃŸ": "ß"
    };

    return data.replace(/Ã\S/g, function (char) {
       if ( replace[char] ) {
           return replace[char];
       } else {
           return char;
       }
    });
}

//V. POST: Kommentar zu dem Lernen einer Kompetenz
function speicherKommentar(linkId, user, context, role, comment, callback) {
    //Ersetze alle Leerzeichen durch "+"
    comment = comment.replace(/ /g, "+");
    $.post("http://localhost:8084/competences/json/link/comment/applink" + konvertiere(linkId).replace(/[^A-Za-z0-9ÄäÖöÜüß]/g, "")
    + "/" + user + "/" + context + "/" + role + "?text=" + comment, callback);
}

//IV. POST: Ein Nutzer hat eine Kompetenz gelernt
function hatKompetenzGelernt(course, creator, role, user, competences, evidences, callback) {
    competences = competences.replace(/ /g, "+")
    var url = "http://localhost:8084/competences/json/link/create/" + course + "/" + creator + "/" + role +
        "/" + user + "?" + "competences=" + competences + "&" + "evidences=" + evidences

    //jQuery erwartet JSON, bekommt aber kein JSON zurück, weshalb man das Parsing von JSON unterbinden muss
    $.ajax({
        "url": url,
        "method": "POST",
        "headers": {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        "dataType": "text",
        //Daten werden an diese Callback-Funktion übergeben
        "success": callback
    });
}
