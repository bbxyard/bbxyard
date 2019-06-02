init:
	mysql -utest -Dtest < db/init.sql


# ####################################
# Dashboard AREA
# ####################################
demo:
	node node/hallo-mysql.js | tee -ai run.log


# ####################################
# Utils AREA
# ####################################
clean:
	rm -rvf *.log
