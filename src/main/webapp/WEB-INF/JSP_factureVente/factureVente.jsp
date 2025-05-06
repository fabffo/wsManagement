<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Facture Vente - Wavy Services</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        .facture-box {
            border: 1px solid #ccc;
            padding: 20px;
            margin: 20px auto;
            max-width: 960px;
            background: #fff;
        }
    </style>
</head>
<body>
<div class="facture-box">
    <h2 class="text-center">Wavy Services</h2>

    <form method="post" action="actionFactureVente">

        <div class="row mt-3">
            <div class="col">
                <strong>N° Facture :</strong> <input type="text" name="numero" class="form-control" />
            </div>
            <div class="col text-end">
                <strong>Date émission :</strong> <input type="date" name="date_facture" class="form-control" />
                <strong>Date échéance :</strong> <input type="date" name="date_echeance" class="form-control" />
            </div>
        </div>

        <div class="row mt-4">
            <div class="col-md-6">
                <label>Émetteur</label>
                <select class="form-control" name="emetteur_id" onchange="fetchAdresseEmetteur(this.value)">
                    <option value="">-- Sélectionner --</option>
                    <c:forEach var="e" items="${emetteurs}">
                        <option value="${e.id}">${e.raison_sociale}</option>
                    </c:forEach>
                </select>
				<input type="hidden" name="raison_sociale_emetteur" id="rs_emetteur">
                <input name="adresse1_emetteur" id="adresse1_emetteur" class="form-control mt-1" readonly>
                <input name="adresse2_emetteur" id="adresse2_emetteur" class="form-control mt-1" readonly>
                <input name="adresse3_emetteur" id="adresse3_emetteur" class="form-control mt-1" readonly>
            </div>

            <div class="col-md-6">
                <label>Client</label>
                <select class="form-control" name="client_id" onchange="fetchAdresseClient(this.value)">
                    <option value="">-- Sélectionner --</option>
                    <c:forEach var="c" items="${clients}">
                        <option value="${c.id}">${c.raison_sociale}</option>
                    </c:forEach>
                </select>
                <input type="hidden" name="raison_sociale_client" id="rs_client">
                <input name="adresse1_client" id="adresse1_client" class="form-control mt-1" readonly>
                <input name="adresse2_client" id="adresse2_client" class="form-control mt-1" readonly>
                <input name="adresse3_client" id="adresse3_client" class="form-control mt-1" readonly>
            </div>
        </div>

        <table class="table mt-4" id="table-details">
            <thead class="table-light">
            <tr>
                <th>Produit</th>
                <th>Quantité</th>
                <th>Prix HT</th>
                <th>TVA (%)</th>
                <th>Montant TVA</th>
                <th>Total TTC</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>
    <select name="produit[]" class="form-control">
        <option value="">-- Sélectionner --</option>
        <c:forEach var="m" items="${missions}">
            <option value="${m.id}">${m.nom}</option>
        </c:forEach>
    </select>
</td>

                <td><input type="number" class="form-control" name="quantite[]" step="1" oninput="recalculer(this)"></td>
                <td><input type="number" class="form-control" name="prix_ht[]" step="0.01" oninput="recalculer(this)"></td>
                <td><input type="number" class="form-control" name="tva[]" step="0.01" oninput="recalculer(this)"></td>
                <td><input type="number" class="form-control" name="montant_tva[]" readonly></td>
                <td><input type="number" class="form-control" name="total_ttc[]" readonly></td>
                <td><button type="button" class="btn btn-danger btn-sm" onclick="this.closest('tr').remove(); recalculerTotal();">-</button></td>
            </tr>
            </tbody>
        </table>
        <button type="button" class="btn btn-secondary" onclick="ajouterLigne()">+ Ajouter une ligne</button>

        <div class="row mt-4">
            <div class="col-md-6"></div>
            <div class="col-md-6">
                <table class="table">
                    <tr><th>Total HT</th><td><input type="number" name="total_ht" class="form-control" readonly></td></tr>
                    <tr><th>Total TVA</th><td><input type="number" name="total_tva" class="form-control" readonly></td></tr>
                    <tr><th>Total TTC</th><td><input type="number" name="total_ttc" class="form-control" readonly></td></tr>
                </table>
            </div>
        </div>
<!-- Footer -->
        <div class="mt-5">
            <p>
                <strong>Conditions de paiement :</strong> Virement sous 30 jours<br>
                <strong>RIB :</strong> FR76 1234 5678 9012 3456 7890 123<br>
                <strong>SIREN :</strong> 123 456 789<br>
                <strong>TVA intracom :</strong> FR123456789
            </p>
        </div>
        <!-- HTML caché pour PDF -->
<textarea name="facture_html" id="facture_html" style="display:none;"></textarea>

<!-- Bouton PDF dans le form -->
<div class="text-end">
    <button type="submit" name="action" value="enregistrer" class="btn btn-success">Enregistrer</button>
	<!-- Ce bouton soumet le HTML préparé en même temps -->
<button type="submit" name="action"  value="visualiser" class="btn btn-secondary" onclick="prepareHtml()">Visualiser le PDF</button>


</div>

    </form>
</div>

<script>
    function ajouterLigne() {
        const tbody = document.querySelector('#table-details tbody');
        const row = tbody.rows[0].cloneNode(true);
        row.querySelectorAll('input').forEach(input => {
            input.value = '';
            if (!input.hasAttribute('readonly')) input.removeAttribute('readonly');
        });
        tbody.appendChild(row);
    }

    function recalculer(input) {
        const row = input.closest('tr');
        const qty = parseFloat(row.querySelector('[name="quantite[]"]').value) || 0;
        const ht = parseFloat(row.querySelector('[name="prix_ht[]"]').value) || 0;
        const taux = parseFloat(row.querySelector('[name="tva[]"]').value) || 0;
        const totalHT = qty * ht;
        const montantTVA = totalHT * taux / 100;
        const totalTTC = totalHT + montantTVA;
        row.querySelector('[name="montant_tva[]"]').value = montantTVA.toFixed(2);
        row.querySelector('[name="total_ttc[]"]').value = totalTTC.toFixed(2);
        recalculerTotal();
    }

    function recalculerTotal() {
        let totalHT = 0, totalTVA = 0, totalTTC = 0;
        document.querySelectorAll('#table-details tbody tr').forEach(row => {
            totalHT += parseFloat(row.querySelector('[name="quantite[]"]').value || 0) *
                       parseFloat(row.querySelector('[name="prix_ht[]"]').value || 0);
            totalTVA += parseFloat(row.querySelector('[name="montant_tva[]"]').value || 0);
            totalTTC += parseFloat(row.querySelector('[name="total_ttc[]"]').value || 0);
        });
        document.querySelector('[name="total_ht"]').value = totalHT.toFixed(2);
        document.querySelector('[name="total_tva"]').value = totalTVA.toFixed(2);
        document.querySelector('[name="total_ttc"]').value = totalTTC.toFixed(2);
    }

    function fetchAdresseClient(id) {
        fetch('clientAdresse?id=' + id)
            .then(res => res.json())
            .then(data => {
                document.getElementById('rs_client').value = data.raison_sociale;
                document.getElementById('adresse1_client').value = data.adresse1;
                document.getElementById('adresse2_client').value = data.adresse2;
                document.getElementById('adresse3_client').value = data.adresse3;
            });
    }

    function fetchAdresseEmetteur(id) {
        fetch('emetteurAdresse?id=' + id)
            .then(res => res.json())
            .then(data => {
                document.getElementById('rs_emetteur').value = data.raison_sociale;
                document.getElementById('adresse1_emetteur').value = data.adresse1;
                document.getElementById('adresse2_emetteur').value = data.adresse2;
                document.getElementById('adresse3_emetteur').value = data.adresse3;
            });
    }

    function prepareHtml() {
        const factureBox = document.querySelector('.facture-box').cloneNode(true);

        // Remplacer tous les champs éditables (input, select, textarea)
        factureBox.querySelectorAll('input, select, textarea').forEach(el => {
            const value = el.value?.trim();
            const span = document.createElement('span');
            span.textContent = value && value !== 'undefined' ? value : '';
            el.parentNode.replaceChild(span, el);
        });

        // Supprimer boutons et scripts
        factureBox.querySelectorAll('button, script').forEach(el => el.remove());

        // Supprimer balises <form> (on garde les enfants)
        const form = factureBox.querySelector('form');
        if (form) {
            const parent = form.parentNode;
            while (form.firstChild) {
                parent.insertBefore(form.firstChild, form);
            }
            parent.removeChild(form);
        }

        // Nettoyer : supprimer tous les <span> vides restants
        factureBox.querySelectorAll('span').forEach(span => {
            if (!span.textContent.trim()) span.remove();
        });

        // Injecter dans le champ caché
        document.getElementById('facture_html').value = factureBox.outerHTML;
    }





    function submitPdf() {
        prepareHtml(); // Remplit le champ caché avec le HTML
        document.querySelector('input[name="action"]').value = "visualiser";
        document.querySelector('form').submit();
    }

    window.addEventListener("DOMContentLoaded", function () {
        const hidden = document.getElementById('facture_html');
        if (hidden) hidden.value = '';
    });



</script>
</body>
</html>
