# Criar Resource Group
az group create --name rm56300-rg --location brazilsouth

# Criar Azure Container Registry (ACR)
az acr create --resource-group rm56300-rg --name rm56300acr --sku Basic --admin-enabled true

# Criar Storage Account para persistir os dados do banco
az storage account create --resource-group rm56300-rg --name rm56300storage --location brazilsouth --sku Standard_LRS

# Criar File Share no Storage Account
az storage share create --account-name rm56300storage --name oracledata