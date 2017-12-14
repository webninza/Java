>**JDK:** This provides developers to develop and execute(run) java programs. It includes the JRE + Development Tools(javac).

>**JRE:** It is the installation package which provides environment to only run the java programs or apps using JVM + Library classes(java).

>**JVM:** JVM is responsible for executing the java programs line by line also called as Interpreter.

JDK, JRE & JVM are platform dependent because configuration of each OS is different but Java is platform independent.

**JVM** is the one that calls the main method from the class file loaded by JRE and created by JDK, This .class file goes into various steps when we run it.
These steps together describle the whole JVM. (https://en.wikipedia.org/wiki/Java_virtual_machine#/media/File:JvmSpec7.png)

>**Class Loader:**
  
  **1. Loading:** Loads the .class file and creates an object of type Class to represent this file in the heap. Class object can be used to get class level information like name of the class, parent name, method name etc.
  
  **2. Linking:** It includes Verification(correctness of .class file)/Preparation(Allocation of Memory)/Resolution(Replacing symbolic references from the type with the direct references).
  
  **3. Initialization:** static block initialization from top to bottom.
  
  In general there are three class loaders:
  
  > - Bootstrap Class Loader : It loads core java API classes present in JAVA_HOME/jre/lib directory (bootstrap path).
  > - Extension Class Loader : It loads classes present in extension directories JAVA_HOME/jre/lib/ext (extension path). implemented in java by the sun.misc.Launcher$ExtClassLoader class.
  > - System/Application class loader : Loads classes from application class path implemented in Java by the sun.misc.Launcher$AppClassLoader class.
  
  >**Class Loader:**
  
    **1. Method Area:** Class level information like class name, immediate parent class name, methods and variables information etc. are stored, including static variables.
    
    **2. Heap Area:** Information of all objects is stored in heap area. There is also one Heap Area per JVM. It is also a shared resource.
    
    **3. Stack Area:** For every thread, JVM creates one run-time stack which is stored here. All local variables of that method are stored in their corresponding frame. After a thread terminates, it's run time stack will be terminated by JVM. Is is not a shared Resource.
    
    **4. PC Registers:** Stores address of the currently executing instruction. Each thread has seperate PC Registers.
    
    **5. Native Method Stacks:** For every thread, separate native stack is created. It stores native method information.
    
>**Execution Engine:** It executes the .class file(bytecode) into machine code. It can be classified into 3 types:
  
    **1. Interpreter:** Line by Line interpretition of the bytecode.
    
    **2. JIT:** Optimizes and Compiles the entire bytecode so reusability happens.
    
    **3. Garbage Collection:** It destroy un-referenced objects.
    
>**JNI:** It enables JVM to call C/C++ libraries and to be called by C/C++ libraries which may be specific to hardware.
  
>**Native Method Libraries:** It is a collection of the Native Libraries(C, C++) which are required by the Execution Engine.
 
 
 
  
