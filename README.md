# Gitlab Instructions (Delete this instruction section when you start the lab)

## Mark down format

Please check the source file.

Bullet items:

* an item
  * a sub item
* another item
* one more item

1. numbered
1. numbered
1. numbered

Italic _italic_

Bold __bold__

Table:

| Task | Assignee | Deadline | Progress (date) |
|---|---|---|---|
| Scope and Stabilizer classes | John Doe | 10/11/18 | Scope done; Stabilizer 50% (10/09/18) |
| Hard copy | John Doe | 10/17/18 | Cannot print: code not finished |

More on [mark down format](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet)

## How to start

### Team Contract

First, find and edit the "team contract" below in this file.

### Initial push to the server:

1. Find out which individual's lab3 to use. Make sure the commit is up-to-date.
1. In a fresh folder `git clone
   https://gitlab.engr.ship.edu/chuo/swe200-lab4-sample`
1. Make sure the following thing are being tracked in that local repo (and
   nothing else). Penalties for unwanted files on gitlab server.
    * `src/`
    * `config/`
    * `README.md` (this file)
    * `build.gradle`
    * `instructor-tests.gradle`
    * `.gitlab-ci.yml` (copy from the sample repo)
    * `.gitignore` (copy from the sample repo. Ideally it should be kept local.
       It's fine for our labs)
1. Find your assigned project repository on our gitlab server
1. Use `git remote add origin <url>` to add the remote `origin`.
1. Use `git push origin master` to push it to the server. `master` is the name
   of the default branch.
1. One of the ways to undo the damage (unwanted files on server):
  1. `git rm --cached <files>`
  1. then `git push ...`

Syncing:

* To pull from the server `git pull origin master`
* To push to the server `git push origin master`
* **THEN** push this README.md up. Do NOT copy and paste this readme using the
  web IDE before you push your code up.

To avoid unnecessary conflicts:

1. ALWAYS `git pull origin master` before you do any local work.
1. If there is a conflict, simply follow the instructions. Basically git will
   mark the difference. You will resolve the conflict, make another commit and
merge the changes.

More on
* [Pro Git 2.5](https://git-scm.com/book/en/v2/Git-Basics-Working-with-Remotes)
* [Pro Git 3.2.Basic Merge Conflicts](https://git-scm.com/book/en/v2/Git-Branching-Basic-Branching-and-Merging)

You may wonder why we are consulting chapter 3 while we just use the master
branch. That's because if the remote master is different from your local
master, it is viewed as a branch. Basically you are dealing a simplified
situation than in 3.2.

* [MIT Missing Semester - Version Control](https://missing.csail.mit.edu/2020/version-control/)


## What to submit

Starting from lab 4, you will be utilizing the SoE gitlab server. So,

Digital copy (on gitlab server):

* Penalties (3 - 10pt) for missing or unnecessary files on the server

PDF on GradeScope:

ONLY __four__ sheets (combined as a single pdf)

1. Instructor's grading sheet (lab4 version)
1. Test requirements sheet
1. Self check sheet
1. Test summary page (just one page) of instructor's tests

One submission on Gradescope per group. Use the __group submission feature__ to
enter ALL the member names in one submission. Penalties for missing team member
names.

## Grading

The rubric is slightly different from the previous ones since now we have a
focus on team collaboration. There are points for how you elaborate your team
contract, how you keep the meeting logs, whether members leave meaningful
commit messages. For example, you cannot leave "update" as the message. You
must describe what you did in this commit. Please read the new "instructor
grading sheet" carefully.

IMPORTANT: Please note that there is requirement for _digital submission_ --
that's whatever you have on gitlab when the lab is due.

### Modern Java Tip

Type casting is bad. The only place to use `instanceof` and type cast is in an
`equals` method.

Good examples:
* Convert a `double d` to `Double`: `Double.valueOf(d)`
* Parse a `String s` to `Double`: `Double.valueOf(s)`
* Convert a `double 1.9` to `int`: `Double.valueOf(1.9).intValue()  => 1`
  truncates like a type cast

### Testing the Weapons and the Attachments

Three weapons and three kinds of attachments: SO MANY combinations. How do we
test them?

See "page 54". Is this plan testing too much? Too little? What is a good
testing plan? How to systematically derive a good test plan in this case?

This is the study of *Software Testing*. A strategy *Base Choice Coverage* can
generate a test plan like it. I can open an elective course if there are enough
people interested in testing.

![](https://images-na.ssl-images-amazon.com/images/I/51H7psCEZzL.jpg)


## Lab 4 FAQ

* Calculation of damage is more complex than the previous labs. Make sure you
  have correct expected values in tests first.
* `rateOfFire` in `GenericWeapon` means the number of shots can be made in a
  single round. `shotsLeft` stores the how many more shots you can fire in this
round. So whenever you have a new round, `shotsLeft` will be reset to
`rateOfFire`.


## Today In-class

* Settings -> General -> Project description, put full names of members in the
  description.
* Finish team contract
* Finish pushing the right files to the repo

THE END OF OUTLINE

(The content above should not be included in your lab repo.)

---

# Lab 4 Description

Please write a brief description here.

# Team Contract

(Answer each of the following questions. Add anything you want at the bottom of
the section.)

**Is it acceptable for one or two team members to do more work than the others
in order to get the team an A?**

Answer: For the most part no, we should try and keep the load as equal as possible.

**How will you use the in-class time?**

Answer: We will spend most of our in-class time figuring out where everyone is at and planning how to do the rest of our tasks. We will talk about any issues we are having to see if other members have any insight. We will also discuss when to schedule out of class meetings.

**How often will you meet outside of class?**

Answer: We will meet at least twice out of class, either in person or over a call on Discord.

**How will work be divided among team members?**

Answer: First we will separate the work load into at least four pieces or more. We will then rate each with a difficulty score between 1-5. Depending on the amount of pieces we initially divided up, we will then give each member approximately the same amount of difficulty score.

(Besides coding, tasks include printing the code and the sheets, etc.)

**What will you do if one or more team members are not doing their share of the work?**

Answer: We will focus on communication. We understand that there can be issues and problems that may present themselves in the code that may cause someone to miss a deadline. As long as there is communication prior to a meeting and deadline the team can adapt and accept changes. We can adapt tasks if a member is struggling to receive help from another. If however, deadlines are not being met with little to no communication, the group will make a majority vote on if a partial grade is necessary. 

(Such decisions must be reported to the instructor in written form. The member
receiving the penalty may switch to another team at the instructor's discretion
after the certain lab is finished.)


**How will you track who is responsible for which tasks and the work progress?**

Answer: We will use a Google Doc to track tasks, progress, and deadlines. We will also include the in-class meetings and the out of class ones.

Link to Google Doc: https://docs.google.com/document/d/1wlEKOTzCqEVpTH9jjg3PzXCm0iDlF_545Q572T4S0H0/edit?usp=sharing_eil_se_dm&ts=6335b83e


# Team Meeting Log

Please also include the in-class meetings. A team MUST meet at least twice out
of class.

