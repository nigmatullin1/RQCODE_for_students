package rqcode.stigs.win10_v3.RegEdit;

import java.util.Map;

import rqcode.stigs.win10_v3.WinScriptHelper;

/**
 * V_220936: Windows 10 systems must use a BitLocker PIN for pre-boot authentication.. 
 */
public class V_220936 extends RegEditStig {

    /**
     * Initiating parameters for the check script
     */
    private final static Map<String, String> CHECK_VALUES = Map.of(
            "path", "HKLM:\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Policies\\System\\Kerberos\\Parameters",
            "attr", "SupportedEncryptionTypes",
            "result_value",  "2147483640",
            "id", "V_220936"
            );
    /**
     * Initiating parameters for the enforce script
     */
    private final static Map<String, String> ENFORCE_VALUES = Map.of(
            "path", "HKLM:\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Policies\\System\\Kerberos\\Parameters",
            "path_short", "HKLM:\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Policies\\System\\Kerberos",
            "attr", "SupportedEncryptionTypes",
            "result_value", "2147483640"
            );
    /**
     * Initiating information defining the security requirements from the STIG
     * database
     */
    private final static Map<String, String> INFO = Map.ofEntries(
            Map.entry("id", "V_220936"),
            Map.entry("title", "Windows 10 systems must use a BitLocker PIN for pre-boot authentication."),
            Map.entry("date", "2021-08-18"),
            Map.entry("ruleID", "SV_220699r569187_rule"),
            Map.entry("severity", "medium"),
            Map.entry("checktext", "If the following registry values do not exist or are not configured as specified, this is a finding.\\nThe policy configures the same Value Name, Type and Value under four different registry paths.\\n\\nRegistry Hive: HKEY_LOCAL_MACHINE\\nRegistry Paths: \\n\\SOFTWARE\\Classes\\batfile\\shell\\runasuser\\\\n\\SOFTWARE\\Classes\\cmdfile\\shell\\runasuser\\\\n\\SOFTWARE\\Classes\\exefile\\shell\\runasuser\\\\n\\SOFTWARE\\Classes\\mscfile\\shell\\runasuser\\\\n\\nValue Name: SuppressionPolicy\\n\\nType: REG_DWORD\\nValue: 0x00001000 (4096)"),
            Map.entry("checkid", "C-22414r642137_chk"),
            Map.entry("fixtext", "Configure the policy value for Computer Configuration >> Windows Settings >> Security Settings >> Local Policies >> Security Options >> \\Network security: Configure encryption types allowed for Kerberos\\ to \\Enabled\\ with only the following selected:\\n\\nAES128_HMAC_SHA1\\nAES256_HMAC_SHA1\\nFuture encryption types"),
            Map.entry("fixid", "F-22403r554583_fix"),
            Map.entry("description","Certain encryption types are no longer considered secure. This setting configures a minimum encryption type for Kerberos, preventing the use of the DES and RC4 encryption suites."),
            Map.entry("iacontrols", "null"),
            Map.entry("version", "WN10-SO-000190")
            );

    /**
     * Setting up STIG information and initializing the windows script helper with
     * the check and enforce parameters
     */
    public V_220936() {
        setStigInfo(INFO);
        WinScriptHelper helper = this.getHelper();
        helper.setCheckValues(CHECK_VALUES);
        helper.setEnforceValues(ENFORCE_VALUES);
    }

    /**
     * Simple test for the STIG check
     */
    public static void main(String[] args) {
        RegEditStig stig = new V_220936();

        stig.check();
        System.out.println(stig);

        // stig.enforce();
        // stig.check();

        //System.out.println(stig);



    }

}
