.PHONY: clean all


# ####################################
# Dashboard AREA
# ####################################
all:
	$(call doNewProj,api)
	$(call doNewProj,vue)
	$(call doNewProj,react)
	$(call doNewProj,standard)
	$(call doNewProj,typescript)

all-by-shell:
	for x in $(shell thinkjs list | grep "-" | awk '{print $2}'); do thinkjs new thinkjs-$$x-demo $$x; done;


# ####################################
# Utils AREA
# ####################################
define doNewProj
	thinkjs new thinkjs-$(1)-demo $(1)
endef

clean:
	rm -rvf *.log *.bak
	find . -type d -name "node_modules" -exec rm -rvf {} \;

