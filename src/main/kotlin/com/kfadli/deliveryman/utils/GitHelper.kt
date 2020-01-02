package com.kfadli.deliveryman.utils

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.revwalk.RevCommit
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider
import java.io.File

object GitHelper {


    fun checkout(url: String, repository: String): Git? {

        val projectName = url.substringAfterLast("/").removeSuffix(".git")
        val target = File(repository.plus("/").plus(projectName))

        return Git.cloneRepository().setURI(url).setDirectory(target).call()
    }

    /*
    fun commit(checkoutDir: File, msg: String): RevCommit {
        //open existing repo and commit and push data.json
        return Git.open(File(checkoutDir, ".git")).use {
            it.add().addFilepattern("data.json").call()
            it.commit().setMessage(msg).call()
        }
    }

    fun pushGist(checkoutDir: File, credentialProvider: UsernamePasswordCredentialsProvider = gitCredentialProvider()) {
        //open existing repo and commit and push data.json
        Git.open(File(checkoutDir, ".git")).use {
            it.push().setCredentialsProvider(credentialProvider).call()
        }
    }
    */
}