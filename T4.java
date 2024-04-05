package org.example.test;

import org.tmatesoft.svn.core.*;
import org.tmatesoft.svn.core.auth.BasicAuthenticationManager;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.*;

import java.io.File;

public class T4 {
    public static void main(String[] args) {
        T4 t = new T4();
        t.doAction();
    }

    private void doAction() {
        DAVRepositoryFactory.setup();

        String svnUrl = "https://desktop-qlseji4/svn/test/trunk/aaa/bbb/ccc/ddd/eee/file.txt";
        SVNClientManager clientManager = SVNClientManager.newInstance();
        try {
            String localFilePath = "E:\\svntest\\aaa\\bbb\\ccc\\ddd\\eee\\file.txt";
            SVNRepositoryFactoryImpl.setup();
            SVNRepository repository = SVNRepositoryFactory.create(SVNURL.parseURIDecoded(svnUrl));
            ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager("yltest", "123qwe");
            repository.setAuthenticationManager(authManager);
            clientManager.setAuthenticationManager(new BasicAuthenticationManager("yltest", "123qwe"));
            clientManager.getUpdateClient().doUpdate(new File[] {new File(localFilePath)}, SVNRevision.HEAD, SVNDepth.INFINITY, false, false);
            SVNStatus status = clientManager.getStatusClient().doStatus(new File(localFilePath), false);
            if (status != null && status.getContentsStatus() == SVNStatusType.STATUS_MODIFIED) {
                SVNCommitClient commitClient = clientManager.getCommitClient();
                commitClient.doCommit(new File[] {new File(localFilePath)}, false, "commitMessage", null, null, false, false, SVNDepth.INFINITY);
                System.out.println("Commit successful");
            } else {
                System.out.println("No changes to commit");
            }
        } catch (SVNException e) {
            if (e instanceof SVNAuthenticationException) {
                System.out.println("SVN Authentication Failed: " + e.getMessage());
            } else {
                System.out.println("SVN Error: " + e.getMessage());
            }
            throw new RuntimeException(e);
        } finally {
            clientManager.dispose();
        }
    }
}
