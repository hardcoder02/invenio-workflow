PROBABILISTIC COMPARISON OPERATORS

I. Introduction
II. Input / output graph format
III. Compiling and Running
IV. Algorithm input / output
V. Supported operators
VI. Extending


I. Introduction
This document describes an implementation of probabilistic comparison operators, which specification is located at:
http://sites.google.com/site/visualanalyticsproject/comparative-operators/comparison-bins

The implementation is based on Invenio and uses an extension of graph input / output format as specified here:
http://users.soe.ucsc.edu/~pang/pigs/data/synthetic/TabDelimIO.txt
---------------------------------------------------------------------------------------



II. Input / output format
The application currently operates on a pair of graphs that adhere to the above mentioned format, with the following
restrictions and extensions:

1. Restrictions:
Because the graphs are loaded using a modification of the reader that is provided along with the graph format
specification (http://users.soe.ucsc.edu/~pang/pigs/data/synthetic/reader/), the application inherits its current
restrictions, such as:
- handles only undirected graphs
- handles only one node file and one edge file (graph file is not necessary and is not handled)
- reads only numeric and categorical features
- no hyper-edges (i.e. only edges with exactly one node at each endpoint)

2. Extensions
The resulting graphs are output using a writer that is also based on the default reader, and therefore, has the
same restrictions as listed above. However, it can write the following feature types, which are necessary to
accommodate the comparison operator output:
- String feature
As described in the graph format specification

- Array feature
An array feature contains a fixed number of elements of another feature type, including another array feature.
It is declared as:
<array_feature declaration> := <array_specification>:<featureid>
<array_specification> := array={<size>}{<labels>}{<feature_declaration>}
where <size> is the declared array size,
<labels> is a comma-separated list of labels, each providing a name for the corresponding element of the array,
similar to a table header. The list must have size <size>, or size 0 if there are no labels.
<feature_declaration> is the contained feature declaration.

For example:
array={2}{DepartmentA,DepartmentB}{numeric:sales}:mySalesArray

The values for the array feature are provided as:
<featurevalue> := <featureid>={ <array_value_list> }
<array_value_list> := (<value>) | (<value>),<array_feature_list>
where <value> is the usual value for the contained feature at the corresponding position in the array.

For example,
mySalesArray={(sales=10),(sales=20)}
---------------------------------------------------------------------------------------



III. Compiling and Running
The archive contains only the comparison operator packages, which are based on Invenio. To compile, extract
the packages to the root of Invenio packages, or create a project from the archive and add Invenio as a library. 

To run:
1. Configure as necessary properties in the following property file:
invenio\operator\cmd\binary_comp.props

NOTE: currently, the only properties are the file names for the two input graphs, and for the three output
graphs, each consisting of a node file and edge file. Also, there is a parameter to overwrite the output files. In
the future, more parameters will be added.

2. Execute:
java invenio.operator.cmd.BinaryComparisonOperator 

NOTE: due to the relatively large size of input graphs and in-memory processing, the heap size
may need to be increased, i.e.:
java invenio.operator.cmd.BinaryComparisonOperator -Xms64m -Xmx512m
---------------------------------------------------------------------------------------



IV. Algorithm input / output
The application reads in two source graphs, applies a set of unary and binary comparison operators,
and outputs three graphs:
- two graphs each corresponding to one of the source graphs, augmented with the results of all unary operators (if any)
- one graph containing the results of all binary operators (if any)
The original source graphs are not modified.

The unary operators apply to each of the two source graphs separately. For each attribute that they handle, they
add a result attribute in the form:
@@@<original_attr>^^^<operator_name>

for example, operator GeneralBin adds the following result attribute for attribute "label": 
@@@label^^^GeneralBin

The binary operators apply to a pair of elements (nodes or edges), one from each graph, where the two elements
have the same id and same schema. They create a result attribute with the same naming as unary operators.


Assumptions:
In order for binary attribute to work, the algorithm assumes that the two source graphs can be
perfectly aligned. I.e. they have the same set of nodes and edges (by id), and the corresponding elements
have the same schema. There is work in progress on handling difference in graph structures.
---------------------------------------------------------------------------------------



V. Supported Operators
At this time, the following operators are supported (see also
 http://sites.google.com/site/visualanalyticsproject/comparative-operators/comparison-bins):

1. Unary operators
a) DataValidationOperator: performs basic validation on a single graph. In particular:
- an element (node or edge) has id
- an element has a schema and it is locked
- the probabilities for categorical attributes are between [0, 1] and add up to 1

b) GeneralBinOperator: for categorical features, places each category into a true or false bin, depending
on whether its probability is >= threshold. By default, the threshold is 0.5.
For example, given a categorical attribute:
cat=0,1,2,3,4:label
and values:
label=3:P=0.10, 0.15, 0.05, 0.55, 0.15
The output should be an attribute:
array={5}{0,1,2,3,4}{string:label}:@@@label^^^GeneralBin
and value:
@@@label^^^GeneralBin={(label=false),(label=false),(label=false),(label=true),(label=false)}


2. Binary operators
a) ComparativeBinOperator: operates on the results of GeneralBinOperator for each of the two graphs.
I.e., for categorical features, returns "high", "opposite", or "low" for each category, based on the bin
where the category was placed by the GeneralBinOperator:
true, true = high
true,false or false,true = opposite
false, false = low
For example, given:
@@@label^^^GeneralBin={(label=false),(label=true),(label=false),(label=true),(label=false)} and
@@@label^^^GeneralBin={(label=false),(label=true),(label=true),(label=false),(label=false)}
the output would be:
@@@label^^^ComparativeBin={(label=low),(label=high),(label=opposite),(label=opposite),(label=low)}

b) MagnitudeDifferenceOperator: for categorical attributes, returns a difference between probabilities
for each category.
For example, given:
label=3:P=0.10, 0.15, 0.05, 0.55, 0.15 and
label=3:P=0.20, 0.20, 0.05, 0.15, 0.40
the output would be:
@@@label^^^MagnitudeDifference={(label=-0.10),(label=-0.05),(label=0.0),(label=0.40),(label=-0.25)}

c) DifferenceSignificanceOperator: for categorical attributes, returns whether the difference between
probabilities for each category is below a certain threshold (by default 0.3).
label=3:P=0.10, 0.15, 0.05, 0.55, 0.15 and
label=3:P=0.20, 0.20, 0.05, 0.15, 0.40
the output would be:
@@@label^^^DifferenceSignificance={(label=false),(label=false),(label=false),(label=true),(label=false)}
---------------------------------------------------------------------------------------


VI. Extending
1. Adding feature types:
- create a feature descriptor by implementing invenio.operator.data.FeatureDescriptor
- create the feature by by implementing invenio.operator.data.Feature
- add the feature descriptor and feature to invenio.operator.data.FeatureDescriptorFactory and
invenio.operator.data.FeatureFactory respectively.
- if necessary, update invenio.operator.io.tab.FeatureDeclarationWriter /
invenio.operator.io.tab.FeatureValueWriter

2. Adding operators:
- Implement invenio.operator.pig.UnaryOperator or invenio.operator.pig.BinaryOperator. Alternatively,
extend invenio.operator.pig.AbstractUnaryOperator or invenio.operator.pig.AbstractBinaryOperator.
- Add the operator to invenio.operator.cmd.BinaryComparisonOperator