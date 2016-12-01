import boto3


region = 'us-east-1a'
instances = ['i-0c646283bf91af08f']

def lambda_handler(event, context):
    ec2 = boto3.client('ec2', region_name=region)
    ec2.stop_instances(InstanceIds=instances)
    print 'stopped instances: ' + str(instances)
