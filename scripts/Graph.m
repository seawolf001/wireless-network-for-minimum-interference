% adjacency matrix is loaded from matrix.txt file
filename='matrix.txt';
[array] = importdata(filename)

% all the AP's names in a list
names = {'ACB0FAP1','ACB0FAP2','ACB1FAP1','ACB1FAP2','ACB1FAP3','ACB1FAP4','ACB2FAP1','ACB2FAP5','ACB2FAP2','ACB2FAP3','ACB2FAP4','ACB3FAP1','ACB3FAP2','ACB3FAP3','ACB3FAP4','ACB4FAP1','ACB4FAP2','ACB4FAP3','ACB4FAP4','ACB5FAP1','ACB5FAP2','ACB5FAP3','ACB5FAP4'}

% X and Y coordinates  of locations of APs in the graph
X=[0.8 1 1 0.5 0.2 0 1 0.8 0.5 0.2 0 0 0.2 0.8 1 0.2 0.5 0.8 1 1 0.5 0.2 0]
Y=[0 0 0.2 0.2 0.2 0.2 0.4 0.4 0.4 0.4 0.4 0.6 0.6 0.6 0.6 0.8 0.8 0.8 0.8 1 1 1 1]

% creating a directed graph using information above
G = digraph(array,names)

%plotting the directed graph with following specification
H=plot(G,'XData',X,'YData',Y,'EdgeLabel',G.Edges.Weight,'LineWidth',1.5,'EdgeColor','blue')
