Run /MainProject as intelijIdea project

admin:
  arthur@gmail.com
  LiesaLot

manager:
  minnie@gmail.com
  NotiGirl

advisor:
  dennis@gmail.com
  Gnasher

DB settings page: (works in offline mode)
  system
  setup

If database breaks beyond repair and backup files are of a bad state as well
	-use "repairQueriesRunManuallyPHP.sql" file in phpmyadmin to fix the DB

Table rows can be clicked to display additional relevant options for row

Not all currencies work in API, stick to common such as: USD, GBP

To use backup and restore you need to:
	-download mySqlServer
	-setup path system variable for mysql cmd commands
	-backups are stored to backup folder

When when assigning ticket need to enter 0 for card no if no card being used

For discount if upper boundary is 0 that is placeholder for infinity
For discount if both boundaries are 0, that represents a flat discount

Only 2 reports generated, ran out of time.
	-Manager Ticket turnover report
	-Manager Gloal Interline+Domestic sales report. Since low on time wanted to display some functionality, beleived comining two reports would be more useful
	-functionality for advisor to generate induvidual domestic and global report is in place but no report generated
	-reports are stored in main folder, need to delete report.pdf to see new generated report.

