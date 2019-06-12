all:
	for x in $(shell thinkjs list | grep "-" | awk '{print $2}'); do thinkjs new thinkjs-$$x-demo $$x; done;
