Write-Host "Stopping existing Java processes..."
Get-Process java -ErrorAction SilentlyContinue | Stop-Process -Force
Start-Sleep -Seconds 2

Write-Host "Starting Eureka Server..."
Start-Process -FilePath ".\mvnw.cmd" -ArgumentList "spring-boot:run" -WorkingDirectory ".\eureka-server" -RedirectStandardOutput "eureka.log" -RedirectStandardError "eureka.err" -WindowStyle Minimized

Write-Host "Waiting for Eureka Server to initialize (20s)..."
Start-Sleep -Seconds 20

Write-Host "Starting Auth Service..."
Start-Process -FilePath ".\mvnw.cmd" -ArgumentList "spring-boot:run" -WorkingDirectory ".\auth-service" -RedirectStandardOutput "auth.log" -RedirectStandardError "auth.err" -WindowStyle Minimized

Write-Host "Starting QMA Service..."
Start-Process -FilePath ".\mvnw.cmd" -ArgumentList "spring-boot:run" -WorkingDirectory ".\qma-service" -RedirectStandardOutput "qma.log" -RedirectStandardError "qma.err" -WindowStyle Minimized

Write-Host "Starting API Gateway..."
Start-Process -FilePath ".\mvnw.cmd" -ArgumentList "spring-boot:run" -WorkingDirectory ".\api-gateway" -RedirectStandardOutput "gateway.log" -RedirectStandardError "gateway.err" -WindowStyle Minimized

Write-Host "Waiting for services to register with Eureka (20s)..."
Start-Sleep -Seconds 20

Write-Host "Starting Frontend..."
Start-Process -FilePath "npm.cmd" -ArgumentList "run dev" -WorkingDirectory ".\frontend"

Write-Host "Project started!"
