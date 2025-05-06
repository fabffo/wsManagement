<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Facture Vente - Wavy Services</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        .facture-box {
            border: 1px solid #ccc;
            padding: 20px;
            margin: 20px auto;
            max-width: 900px;
            background: #fff;
        }
    </style>
</head>
<body>
<div class="facture-box">
    <h2 class="text-center">Wavy Services</h2>

    <form method="post" action="/factureVente">
        <!-- Facture header -->
        <div class="row mt-3">
            <div class="col">
                <strong>N° Facture :</strong> F-0001
            </div>
            <div class="col text-end">
                <strong>Date émission :</strong> 25/03/2025<br>
                <strong>Date échéance :</strong> 25/04/2025
            </div>
        </div>

        <!-- Infos émetteur/client -->
        <div class="row mt-4">
            <div class="col-6">
                <h5>Émetteur :</h5>
                <p>
                    Wavy Services SARL<br>
                    12 rue de Paris<br>
                    75000 Paris<br>
                    contact@wavyservices.fr
                </p>
            </div>
            <div class="col-6">
                <h5>Client :</h5>
                <p>
                    Nom Client<br>
                    45 avenue du Client<br>
                    69000 Lyon<br>
                    client@mail.com
                </p>
            </div>
        </div>

        <!-- Détail lignes produit -->
        <table class="table mt-4" id="table-details">
            <thead class="table-light">
            <tr>
                <th>Libellé</th>
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
                <td><input type="text" class="form-control" name="libelle[]"></td>
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

        <!-- Totaux -->
        <div class="row mt-4">
            <div class="col-md-6"></div>
            <div class="col-md-6">
                <table class="table">
                    <tr>
                        <th>Total HT</th>
                        <td><input type="number" name="total_ht" class="form-control" readonly></td>
                    </tr>
                    <tr>
                        <th>Total TVA</th>
                        <td><input type="number" name="total_tva" class="form-control" readonly></td>
                    </tr>
                    <tr>
                        <th>Total TTC</th>
                        <td><input type="number" name="total_ttc" class="form-control" readonly></td>
                    </tr>
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

        <div class="text-end">
            <button type="submit" name="action" value="enregistrer" class="btn btn-success">Enregistrer</button>
			<button type="submit" name="action" value="visualiser" class="btn btn-secondary">Visualiser le PDF</button>
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
</script>
</body>
</html>
