ACR_PASSWORD=$(az acr credential show --name rm56300acr --query "passwords[0].value" --output tsv)
DB_FQDN=$(az container show --resource-group rm56300-rg --name rm56300-db --query ipAddress.fqdn --output tsv)

# Criar o Container da API Java (ACI) apontando para o Banco
az container create \
  --resource-group rm56300-rg \
  --name rm56300-app \
  --image rm56300acr.azurecr.io/rm56300-app:latest \
  --os-type Linux \
  --cpu 1 \
  --memory 1 \
  --registry-login-server rm56300acr.azurecr.io \
  --registry-username rm56300acr \
  --registry-password $ACR_PASSWORD \
  --dns-name-label rm56300-app-dns \
  --ports 8080 \
  --environment-variables SPRING_DATASOURCE_URL="jdbc:oracle:thin:@$DB_FQDN:1521/FREEPDB1" SPRING_DATASOURCE_USERNAME="system"