# Obter senhas do Storage e do ACR
STORAGE_KEY=$(az storage account keys list --resource-group rm56300-rg --account-name rm56300storage --query "[0].value" --output tsv)
ACR_PASSWORD=$(az acr credential show --name rm56300acr --query "passwords[0].value" --output tsv)

# Criar o Container do Banco (ACI) com volume persistente
az container create \
  --resource-group rm56300-rg \
  --name rm56300-db \
  --image rm56300acr.azurecr.io/rm56300-db:latest \
  --registry-login-server rm56300acr.azurecr.io \
  --registry-username rm56300acr \
  --registry-password $ACR_PASSWORD \
  --dns-name-label rm56300-db-dns \
  --ports 1521 \
  --azure-file-volume-account-name rm56300storage \
  --azure-file-volume-account-key $STORAGE_KEY \
  --azure-file-volume-share-name oracledata \
  --azure-file-volume-mount-path /opt/oracle/oradata