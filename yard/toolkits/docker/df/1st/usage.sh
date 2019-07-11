# 导入
docker build --rm=true -t "apk-bash${TAG}" - < "../df/apk-bash.dockerfile"
docker build --rm=true -t "apk-node${TAG}" - < "../df/apk-node.dockerfile"
docker build --rm=true -t "os-u18${TAG}" - < "../df/u18-init.dockerfile"
docker build --rm=true -t "os-c7${TAG}" - < "../df/c7-init.dockerfile