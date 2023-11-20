$path = "HKLM:\\SOFTWARE\\Policies\\Microsoft\\Windows NT\\Printers"
$attr = "DisableHTTPPrinting"
$result_value = "1"
$result = "ERROR"
if (Test-Path $path){
$var1 = Get-ItemPropertyValue -Path $path -Name $attr
if ($var1 -eq $result_value){
$result = "OK"
}
}
$result