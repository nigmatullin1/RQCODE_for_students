package rqcode.stigs.win10_new.Categories.UserRightsTests;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/*
 * Generate classes for STIGs:
 * V_220982
 * V_220983
 * V_220980
 * V_220981
 * V_220966
 * V_220967
 * V_220960
 * V_220962
 * V_220979
 * V_220978
 * V_220976
 * V_220974
 */

public class UserRightsGenerateTest2 {

    /**
     * @throws IOException
     */
    @Test
    public void generateUserRights() throws IOException {
        // Initialize the Velocity engine
        VelocityEngine velocityEngine = new VelocityEngine();
        ObjectMapper mapper = new ObjectMapper();

        Properties props = new Properties();
        props.put(RuntimeConstants.RESOURCE_LOADER, "file,class,classpath");
        props.put("file.resource.loader.path", "classpath");
        props.put("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        velocityEngine.init(props);

        String content = Files.readString(Path.of("src/test/resources/templates/userRights/userrights2.json"), StandardCharsets.UTF_8);
        List<UserRightsTemplate> userRightTemplates = new ArrayList<>();

        userRightTemplates = Arrays.asList(mapper.readValue(content, UserRightsTemplate[].class));

        // Load the template
        Template template = velocityEngine.getTemplate("templates/userRights/UserRightsTemplate.vm");

        for (UserRightsTemplate userRightsTemplate : userRightTemplates) {
            VelocityContext context = new VelocityContext();

            context.put("id", userRightsTemplate.getId());
            context.put("constantName", userRightsTemplate.getConstantName());        
            context.put("date", userRightsTemplate.getDate());
            context.put("severity", userRightsTemplate.getSeverity());
            context.put("version", userRightsTemplate.getVersion());
            context.put("description", userRightsTemplate.getDescription());
            context.put("title", userRightsTemplate.getTitle());
            context.put("checktext", userRightsTemplate.getChecktext());
            context.put("checkid", userRightsTemplate.getCheckid());
            context.put("fixtext", userRightsTemplate.getFixtext());
            context.put("fixid", userRightsTemplate.getFixid());
            context.put("iacontrols", userRightsTemplate.getIacontrols());
            context.put("ruleID", userRightsTemplate.getRuleID());
            context.put("stigtype","UserRightsStig2");

            
            // Generate the Java code
            StringWriter writer = new StringWriter();
            template.merge(context, writer);

            Files.write( Paths.get("src/main/java/rqcode/stigs/win10_v3/UserRights/" + userRightsTemplate.getId() + ".java"), writer.toString().getBytes());
        }
    }
}
