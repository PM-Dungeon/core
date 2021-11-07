# pmdungeon

## Docker

**Linux/MacOS:**
- `cd code`
- Run: `docker run --rm -it -p 8080:8080 -u "$(id -u):$(id -g)" -v "$(pwd):/code" -w "/code" gradle:7.2.0-jdk11 gradle html:superDev`
- Or run: `./docker_run.sh`

**Windows:**
- Open Windows PowerShell (in Administrator Mode)
- `cd code`
- Run: `docker run --rm -it -p 8080:8080 -u "${id -u}:${id -g}" -v "${pwd}:/code" -w "/code" gradle:7.2.0-jdk11 gradle html:superDev`
- Or run: `.\docker_run.ps1`

(**Hints:** To run the PowerShell script, you have to allow such scripts: `set-executionpolicy remotesigned`)
