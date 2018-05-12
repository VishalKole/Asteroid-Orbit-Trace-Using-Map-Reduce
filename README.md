# Asteroid-Orbit-Trace-Using-Map-Reduce


An asteroid follows an elliptical orbit around the sun. The aphelion is the point in the orbit farthest away from the sun. The perihelion is the point in the orbit closest to the sun. The aphelion distance (AD) is the distance from the sun to the aphelion. The perihelion distance (PD) is the distance from the sun to the perihelion. AD and PD are measured in astronomical units (AUs), where 1 AU is the distance from the earth to the sun, about 93 million miles or 150 million kilometers.

The [AstDyS-2 web site](http://hamilton.dm.unipi.it/astdys/) maintains a dataset of asteroid orbit data. Each line of the file comprises orbit data for one asteroid. Here is an example:
```
'1'            58000.000000   2.7674093196380953E+00   7.5607320406440415E-02   1.0593216347797092E+01   8.0308878085741753E+01   7.3023722835643468E+01   3.0949408336762878E+02  3.44  0.12  0
```
The first field is an asteroid index (an integer). The third field is the orbit's semimajor axis (a) in AU. The fourth field is the orbit's eccentricity (e). The dataset contains information on **over 500,000 asteroids**.

We want to know which asteroids could possibly hit the earth, or any other planet in the solar system. We will analyze this by specifying two distances from the sun, mindist and maxdist (AU). These specify an annular region centered on the sun. If any point in an asteroid's orbit intersects this annular region, we will report that asteroid.

Here is an example of the analysis program running on a cluster. The program found 1437 asteroids whose orbits intersect the annular region from 0.999 to 1.001 AU from the sun.

```
$ java pj2 jar=p4.jar threads=4 AstOrbit dr00,dr01,dr02,dr03,dr04,dr05,dr06,dr07,dr08,dr09 /var/tmp/vvk3025/asteroid/astorbits.txt 0.999 1.001
Job 25 launched XXXX
Job 25 started XXXX
1566	0.18669	1.9695
1620	0.82760	1.6629
1685	0.77105	1.9639
1862	0.64721	2.2930
1863	0.88950	3.6293
1864	0.56339	2.3586
1865	0.57575	1.5844
1866	0.87368	2.9131
1981	0.62116	2.9307
2062	0.79006	1.1435
. . .
504034	0.56236	6.1244
504074	0.53473	1.5621
504181	0.13539	2.9409
504680	0.88889	2.5337
504711	0.87320	1.8382
504800	0.79140	1.2327
505178	0.81846	2.8125
505657	0.90441	1.6939
506074	0.48764	2.6287
1437
Job 25 finished XXXX time 5776 msec
```

## Software Specification

The program must be run by typing this command line on a cluster:

* java pj2 jar=`<jar>` threads=`<NT>` AstOrbit `<nodes>` `<file>` `<mindist>` `<maxdist>`
	* `<jar>` is the name of the JAR file containing all of the program's Java class files.
	* `<NT>` is the number of mapper threads per mapper task; if omitted, the default is 1.
	* `<nodes>` is a comma-separated list of cluster node names on which to run the analysis. One or more node names must be specified.
	* `<file>` is the name of the file on each node's local hard disk containing the asteroid orbit data to be analyzed.
	* `<mindist>` is the minimum distance for the annular region in AU. It must be a double precision floating point number ≥ 0.0.
	* `<maxdist>` is the maximum distance for the annular region in AU. It must be a double precision floating point number ≥ mindist.

#### If the command line does not have the required number of arguments, if any argument is erroneous, or if any other error occurs, the program will print an error message on the console and must exit.

