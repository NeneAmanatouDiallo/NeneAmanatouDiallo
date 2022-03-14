//import java.util.Arrays;

public class TestTimes implements TestTimesInterface {
  
private double [] testTimes = new double [10];
private double [] memU = new double[10];
  
private int counter =0;
private TimeUnits timeUnits;
private double seconds = 1000000000;
private double milliSeconds = 1000000;
private double microSeconds = 1000;
private MemoryUnits memoryUnits;
private double kiloBytes = 1024;
private double megaBytes = 1048576;
  

@Override
public void addTestTime(long runTime) {

if (this.counter < this.testTimes.length) {
this.testTimes[this.counter] = runTime;
this.memU[this.counter] = ((Runtime.getRuntime().totalMemory()) - (Runtime.getRuntime().freeMemory()));
this.counter++;

}

else {
for (int i = 0; i < (this.testTimes.length - 1); i++) {
this.testTimes[i] = this.testTimes[i + 1];
this.memU[i] = this.memU[i + 1];

}
this.memU[this.memU.length - 1] = ((Runtime.getRuntime().totalMemory())
- (Runtime.getRuntime().freeMemory()));
this.testTimes[this.testTimes.length - 1] = runTime;

}

}

@Override
public double getAverageTestTime() {
  
double count = 0;
  
for (int i = 0; i < counter; i++) {
count += testTimes [i];
}
double meanRunTime = (double) count / counter;

if (this.timeUnits == TimeUnits.Seconds) {
return meanRunTime / this.seconds;
}
else if (this.timeUnits == TimeUnits.MilliSeconds) {
return meanRunTime / this.milliSeconds;
}
else if (this.timeUnits == TimeUnits.MicroSeconds) {
return meanRunTime / this.microSeconds;
}
else {
return meanRunTime;
}
}

  

@Override
public double getLastTestTime() {
  
if (this.timeUnits == TimeUnits.Seconds) {
return ((this.testTimes[this.counter - 1]) / this.seconds);
} else if (this.timeUnits == TimeUnits.MilliSeconds) {
return ((this.testTimes[this.counter - 1]) / this.milliSeconds);
} else if (this.timeUnits == TimeUnits.MicroSeconds) {
return ((this.testTimes[this.counter - 1]) / this.microSeconds);
} else {
// nanoseconds
return this.testTimes[this.counter - 1];
}

}
  

@Override
public double[] getTestTimes() {
if (this.timeUnits == TimeUnits.Seconds) {
double[] secArray = new double[10];
for (int i = 0; i < 10; i++) {
secArray[i] = (this.testTimes[i]) / this.seconds;
}
return secArray;
}

  
else if (this.timeUnits == TimeUnits.MilliSeconds) {
double[] milliArray = new double[10];
for (int i = 0; i < 10; i++) {
milliArray[i] = (this.testTimes[i]) / this.milliSeconds;
}
return milliArray;
}

else if (this.timeUnits == TimeUnits.MicroSeconds) {
double[] microArray = new double[10];
for (int i = 0; i < 10; i++) {
microArray[i] = (this.testTimes[i]) / this.microSeconds;
}

return microArray;
}
// nanoseconds
return testTimes;
}

@Override
public void resetTestTimes() {
this.testTimes = new double[10];
this.memU = new double[10];

// reset the runTimeCounter
this.counter = 0;
}

@Override
public TimeUnits getTimeUnits() {
// TODO Auto-generated method stub
return this.timeUnits;
}


@Override
public void setTimeUnits(TimeUnits timeUnits) {
// TODO Auto-generated method stub
this.timeUnits = timeUnits;
  
}


@Override
public MemoryUnits getMemoryUnits() {
// TODO Auto-generated method stub
return this.memoryUnits;
}


@Override
public void setMemoryUnits(MemoryUnits memoryUnits) {
// TODO Auto-generated method stub
this.memoryUnits = memoryUnits;
}


  


@Override
public double getLastMemoryUsage() {
// TODO Auto-generated method stub
if (this.memoryUnits == MemoryUnits.KiloBytes) {
return (this.memU[this.counter - 1]) / this.kiloBytes;
} else if (this.memoryUnits == MemoryUnits.MegaBytes) {
return (memU[this.counter - 1]) / this.megaBytes;
} else {
// bytes
return memU[counter - 1];
}

}


  


@Override
public double[] getMemoryUsages() {
// TODO Auto-generated method stub
if (this.memoryUnits == MemoryUnits.KiloBytes) {
double[] kiloArray = new double[10];
for (int i = 0; i < 10; i++) {
kiloArray[i] = (this.memU[i]) / this.kiloBytes;
}
return kiloArray;
}

else if (this.memoryUnits == MemoryUnits.MegaBytes) {
double[] megArray = new double[10];
for (int i = 0; i < 10; i++) {
megArray[i] = (this.memU[i]) / this.megaBytes;
}
return megArray;
}
// bytes
return memU;
}


@Override
public double getAverageMemoryUsage() {
double count = 0;
double meanMemoryUsage;

for (int i = 0; i < counter; i++) {
count += this.memU[i];
}
meanMemoryUsage = count / this.counter;

if (this.memoryUnits == MemoryUnits.KiloBytes) {
return meanMemoryUsage / this.kiloBytes;
}
else if (this.memoryUnits == MemoryUnits.MegaBytes) {
return meanMemoryUsage / this.megaBytes;
}
  
return meanMemoryUsage;
}
  }