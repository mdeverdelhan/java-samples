/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2017 Marc de Verdelhan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eu.verdelhan.samples.ssh;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.common.IOUtils;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.connection.channel.direct.Session.Command;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;

public class SshClientExample {

    public static void main(String[] args) {

        try (SSHClient sshClient = new SSHClient()) {
            
            String hostname = "localhost";
            int port = 22;
            String username = "username";
            String password = "password";
            
            // Connection & authentication
            sshClient.addHostKeyVerifier(new PromiscuousVerifier());
            sshClient.connect(hostname, port);
            sshClient.authPassword(username, password);
            
            try (Session session = sshClient.startSession()) {
                // Find all .txt files
                Command cmd = session.exec("find \"`pwd -P`\" -iname \"*.txt\"");
                System.out.println(IOUtils.readFully(cmd.getInputStream()).toString());
                
                cmd.join(30, TimeUnit.SECONDS);
                
                // Exit status of the command
                System.out.println("\n** exit status: " + cmd.getExitStatus());
            }
            
            // Disconnect
            sshClient.disconnect();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
