pipelineJob('XXXXXXXXX'){ // Add Jenkins File Name in place of XXXXXXXXX. Check your individual Jenkins Setup and make necessary changes here.
  displayName('Github PR Trigger')
  description('Trigger on every Github PR merge button click')
  parameters {
    // Individual trigger for different enviornment
    choiceParam('DEPLOY_ENV', ['env1', 'env2', 'env3', 'env4', 'env5'], 'Select enviornment')
  }
  triggers {
    ghprbTrigger {
      adminlist("")
      whitelist("")
      orgslist("")
      cron("H/5 * * * *") // This will run on every 5 mintues H/5.
      // When filled, commenting this phrase in the pull request will trigger a merge.
      triggerPhrase("Ready to Merge")
      // When checked, only commenting the trigger phrase in the pull request will trigger a merge.
      onlyTriggerPhrase(true)
      // Checking this option will disable regular polling (cron) for changes in GitHub and will try to create a GitHub hook.
      useGitHubHooks(true)
      // This is dangerous!!!
      permitAll(true)
      autoCloseFailedPullRequests(false)
      displayBuildErrorsOnDownstreamBuilds(false)
      whiteListTargetBranches {
        ghprbBranch {
          branch("env1")
        }
        ghprbBranch {
          branch("env2")
        }
        ghprbBranch {
          branch("env3")
        }
        ghprbBranch {
          branch("env4")
        }
        ghprbBranch {
          branch("env5")
        }
      }
      commentFilePath("")
      // When filled, adding this phrase to the pull request title or body will not trigger a build.
      skipBuildPhrase("")
      // When filled, pull request commits from this user(s) will not trigger a build.
      blackListCommitAuthor("")
      // Use this option to allow members of whitelisted organisations to behave like admins, i.e. whitelist users and trigger pull request testing.
      allowMembersOfWhitelistedOrgsAsAdmin(false)
      msgSuccess("Merged Success")
      msgFailure("Merged Failed")
      commitStatusContext("deploy to staging site")
      gitHubAuthId("https://api.github.com : Anonymous connection")
      // Set the build description.
      buildDescTemplate("")
      blackListLabels("")
      whiteListLabels("")
      includedRegions("")
      // Each exclusion uses regular expression pattern matching, and must be separated by a new line.
      excludedRegions("")
      extensions {
        ghprbSimpleStatus {
          commitStatusContext('deploy to staging site')
          startedStatus('Deploy Started')
          triggeredStatus('Deploy Triggered')
          statusUrl("")
          addTestResults(true)
          showMatrixStatus(false)
          completedStatus {
            ghprbBuildResultMessage {
              message("Deploy Successful")
              result('SUCCESS')
            }
            ghprbBuildResultMessage {
              message("Deploy Failed. Investigate!")
              result('FAILURE')
            }
            ghprbBuildResultMessage {
              message("Deploy in progress...")
              result('PENDING')
            }
            ghprbBuildResultMessage {
              message("Error. Please Investigate!")
              result('ERROR')
            }
          }
        }
      }
    }
  }

  // Check your Jenkins Setup and make necessary changes here based on that.
  definition {
    cps {
      // Add Jenkins File Path in place of XXXXXXXXX.
      script(readFileFromWorkspace('XXXXXXXXXXXX'))
      sandbox()
    }
  }
}